package web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import web.dao.ImageRepository;
import web.dto.ImageDTO;
import web.model.Image;
import web.model.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ObjectMapper objectMapper;

    private final ImageRepository imageRepository;

    @Value("${google-search.api-key}")
    private String googleSearchKey;

    @Value("${google-search.engine-id}")
    private String engineId;

    @Override
    public Image searchImages(String prompt) throws JsonProcessingException {
        String response = WebClient.builder().baseUrl("https://www.googleapis.com")
                .build().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/customsearch/v1")
                        .queryParam("key", googleSearchKey)
                        .queryParam("cx", engineId)
                        .queryParam("q", prompt)
                        .queryParam("searchType", "image")
                        .build())
                .retrieve()
                .toEntity(String.class).block().getBody();
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        String link = objectMapper.readTree(jsonArray.getJSONObject(0).toString()).path("link").asText();
        Image image = new Image(link);
        imageRepository.save(image);
        return image;
    }

    @Override
    public Page<ImageDTO> findAll(int size, int page, String sortBy) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 10),Sort.Direction.ASC, sortBy);
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
