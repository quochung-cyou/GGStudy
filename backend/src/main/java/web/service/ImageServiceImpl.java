package web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import web.common.exception.NotFoundException;
import web.common.shared.ContentType;
import web.common.utils.PageableUtils;
import web.model.Element;
import web.model.Image;
import web.dao.repository.ImageRepository;
import web.model.Project;
import web.model.Slide;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ObjectMapper objectMapper;

    private final ImageRepository imageRepository;

    @Value("${google-search.api-key}")
    private String googleSearchKey;

    @Value("${google-search.engine-id}")
    private String engineId;

    public final ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Override
    public Image searchImage(String prompt) throws JsonProcessingException {
        return searchWithImprovePrompt(prompt, true);
    }

    @Override
    @Transactional
    public void setUrlImageElement(Project project) {
        List<CompletableFuture<Pair<String, Image>>> futures = new ArrayList<>();
        Map<String, Image> mapImage = new HashMap<>();
        for (Slide slide : project.getSlides()) {
            for (Element element : slide.getElements()) {
                if (element.getElementType().equals(ContentType.IMAGE.toString())) {
                    CompletableFuture<Pair<String, Image>> mono = Mono.fromCallable(() -> {
                        element.setId(UUID.randomUUID().toString());
                        try {
                            return Pair.of(element.getId(), searchImage(element.getImageUrl()));
                        } catch (Exception e) {
                            log.error("Error searching image for element: {}", e.getMessage(), e);
                            return null;
                        }
                    }).subscribeOn(Schedulers.fromExecutor(executorService)).toFuture();
                    futures.add(mono);
                }
            }
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        List<Image> list = new ArrayList<>();
        CompletableFuture<List<Image>> allImagesFuture = allOf.thenApply(v -> futures.stream()
                .map(future -> {
                    try {
                        mapImage.put(future.get().getFirst(), future.get().getSecond());
                        return future.get().getSecond();
                    } catch (Exception e) {
                        log.error("Error setting image for element: {}", e.getMessage(), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        allImagesFuture.thenAccept(list::addAll).join();
        for (Slide slide : project.getSlides()) {
            for (Element element : slide.getElements()) {
                if (element.getElementType().equals(ContentType.IMAGE.toString())) {
                    element.setImageUrl(mapImage.get(element.getId()).getLink());
                    element.setId(null);
                }
            }
        }
        imageRepository.saveAll(list);
        log.info("Image saved successfully");
    }

    @Transactional
    private Image searchWithImprovePrompt(String prompt, boolean improvePrompt) throws JsonProcessingException {
        String newPrompt = prompt;
        if (improvePrompt) newPrompt = improvePrompt(prompt);
        log.info("Searching for images with prompt: {}", newPrompt);
        String finalPrompt = newPrompt;
        String response = WebClient.builder().baseUrl("https://www.googleapis.com")
                .build().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/customsearch/v1")
                        .queryParam("key", googleSearchKey)
                        .queryParam("cx", engineId)
                        .queryParam("q", finalPrompt)
                        .queryParam("searchType", "image")
                        .queryParam("num", 1)
                        .queryParam("imgSize", "large")
                        .queryParam("fields", "items(link)")
                        .build())
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            log.error("Error response from server: {}", errorBody);
                            return Mono.error(new RuntimeException("Error response from server: " + clientResponse.statusCode()));
                        })
                )
                .toEntity(String.class)
                .retry(3)
                .block()
                .getBody();

        JSONObject jsonObject = new JSONObject(response);
        if (!jsonObject.has("items") || jsonObject.getJSONArray("items").isEmpty()) {
            log.error("Cannot find image with prompt: {}", prompt);
            return searchWithImprovePrompt(prompt, false);
        }
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        String link = objectMapper.readTree(jsonArray.getJSONObject(0).toString()).path("link").asText();
        log.info("Image link: {}", link);
        return new Image(link);
    }

    public String improvePrompt(String prompt) {
        return prompt +
                " site:freepik.com" +
                " OR site:unsplash.com" +
                " OR site:pexels.com" +
                " OR site:pixabay.com" +
                " OR site:istockphoto.com" +
                " OR site:freeimages.com" +
                " OR site:stocksnap.io" +
                " OR site:stockvault.net";
    }

    @Override
    public Page<Image> findAll(int size, int page, String sortBy) {
        Pageable pageable = PageableUtils.createPageable(size, page, sortBy);
        return imageRepository.findAll(pageable);
    }

    @Override
    public Image findById(String id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isEmpty()) throw new NotFoundException("Image not found - " + id);
        return image.get();
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void deleteById(String id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isEmpty()) throw new NotFoundException("Image not found - " + id);
        imageRepository.delete(image.get());
    }

}
