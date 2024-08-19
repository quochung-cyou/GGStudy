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
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import web.common.exception.NotFoundException;
import web.common.utils.PageableUtils;
import web.model.Image;
import web.dao.repository.ImageRepository;

import java.util.Optional;

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

    @Override
    public Image searchImages(String prompt) throws JsonProcessingException {
        prompt = improvePrompt(prompt);
        log.info("Searching for images with prompt: {}", prompt);
        String finalPrompt = prompt;
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
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        String link = objectMapper.readTree(jsonArray.getJSONObject(0).toString()).path("link").asText();
        log.info("Image link: {}", link);
        Image image = new Image(link);
        imageRepository.save(image);
        return image;
    }

    public String improvePrompt(String prompt) {
        return prompt +  " high resolution" +
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
