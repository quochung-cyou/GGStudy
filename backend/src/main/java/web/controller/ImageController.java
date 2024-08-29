package web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.common.dto.CustomResponse;
import web.model.Image;
import web.service.ImageService;


@RestController
@RequestMapping("/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping()
    public CustomResponse<Page<Image>> findAll(
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "id") String sortBy) {
        return new CustomResponse<>(imageService.findAll(size, page, sortBy));
    }

    @PostMapping()
    public CustomResponse<Image> getImages(@RequestParam String prompt) throws JsonProcessingException {
        return new CustomResponse<>(imageService.searchImage(prompt));
    }

    @GetMapping("/{id}")
    public CustomResponse<Image> findById(@PathVariable String id) {
        return new CustomResponse<>(imageService.findById(id));
    }

    @PutMapping()
    public CustomResponse<Image> update(@RequestBody Image image) {
        return new CustomResponse<>(imageService.save(image));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // 204 status code for successful deletion without content
    public void deleteById(@PathVariable String id) {
        imageService.deleteById(id);
    }

}
