package web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import web.dao.ImageRepository;
import web.dto.ImageDTO;
import web.model.Image;
import web.model.NotFoundException;

import java.util.ArrayList;
import java.util.List;
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
        return prompt + " high resolution" +
                " no watermark" +
                " free license";
    }

    @Override
    public Page<ImageDTO> findAll(int size, int page, String sortBy) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 10), Sort.Direction.ASC, sortBy);
        List<Image> images = imageRepository.findAll();
        List<ImageDTO> imageDTOList = new ArrayList<>();
        for (Image image : images) {
            ImageDTO imageDTO = new ImageDTO(image);
            imageDTOList.add(imageDTO);
        }
        return new PageImpl<>(imageDTOList, pageable, imageDTOList.size());
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
