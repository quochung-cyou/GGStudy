package web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.common.dto.CustomResponse;
import web.model.*;
import web.service.OutlineService;

import java.io.IOException;

@RestController
@RequestMapping("/v1/outlines")
@RequiredArgsConstructor
public class OutlineController {

    private final OutlineService outlineService;

    @GetMapping()
    public CustomResponse<Page<Outline>> findAll(
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "id") String sortBy) {
        return new CustomResponse<>(outlineService.findAll(size, page, sortBy));
    }

    @PostMapping()
    public CustomResponse<Outline> createOutline(@RequestParam String topicName, @RequestParam String additionalInfo) throws IOException {
        return new CustomResponse<>(outlineService.createOutlineFromGemini(topicName, additionalInfo));
    }
    @GetMapping("/{id}")
    public CustomResponse<Outline> findById(@PathVariable String id) {
        return new CustomResponse<>(outlineService.findById(id));
    }


    @PutMapping()
    public CustomResponse<Outline> update(@RequestBody Outline outline) {
        return new CustomResponse<>(outlineService.save(outline));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // 204 status code for successful deletion without content
    public void deleteById(@PathVariable String id) {
        outlineService.deleteById(id);
    }

}
