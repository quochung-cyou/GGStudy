package web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import web.dao.ImageRepository;
import web.model.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ObjectMapper objectMapper;

    private final ImageRepository imageRepository;

    @Autowired
    private WebClient webClientImage;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    @Override
    public List<Image> collectImages(String prompt, String apiKey, String searchEngineId) {
        String response = webClientImage.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/customsearch/v1")
                        .queryParam("key", apiKey)
                        .queryParam("cx", searchEngineId)
                        .queryParam("q", prompt)
                        .queryParam("searchType", "image")
                        .build())
                .retrieve()
                .toEntity(String.class).block().getBody();
        List<Image> images = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                String link = objectMapper.readTree(jsonArray.getJSONObject(i).toString()).path("link").asText();
                Image image = new Image(link);
                images.add(imageRepository.save(image));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return images;
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image findById(String id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isEmpty()) throw new RuntimeException("Image not found - " + id);
        return image.get();
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void deleteById(String id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isEmpty()) throw new RuntimeException("Image not found - " + id);
        imageRepository.delete(image.get());
    }

}
