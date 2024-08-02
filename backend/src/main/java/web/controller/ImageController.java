package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.model.Image;
import web.service.ImageService;

import java.util.List;

@RestController
@RequestMapping("/v1/images")
public class ImageController {

    @Autowired
    private ImageService imagesService;

    @Autowired
    private ImageService imageService;

    @Value("${google-search.api-key}")
    private String googleSearchKey;

    @Value("${google-search.engine-id}")
    private String engineId;

    public ImageController(ImageService imagesService) {
        this.imagesService = imagesService;
    }

    @GetMapping("")
    public List<Image> findAll() {
        return imagesService.findAll();
    }

    @PostMapping("")
    public List<Image> getPhotos(@RequestParam String prompt) {
        return imageService.collectImages(prompt, "AIzaSyDg7dXjcHuw-JEWr73I9GxfnKGUN_ucmT0", "a390f8bec5fc54a56");
//        return imageService.collectImages(prompt, googleSearchKey, engineId);
    }

    @GetMapping("/{id}")
    public Image findById(@PathVariable String id) {
        return imagesService.findById(id);
    }
    
    @PutMapping("")
    public Image update(@RequestBody Image image) {
        return imagesService.save(image);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // 204 status code for successful deletion without content
    public void deleteById(@PathVariable String id) {
        imagesService.deleteById(id);
    }

}
