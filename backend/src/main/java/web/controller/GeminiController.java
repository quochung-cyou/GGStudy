package web.controller;

import org.springframework.beans.factory.annotation.Value;
import web.common.io.ResourceLoader;
import web.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GeminiController {

    @Autowired
    GeminiService geminiService;

    @Value("${gemini.api-key}")
    private String geminiKey;

    @GetMapping("/v1/prompts")
    public String getResponse() {
        ResourceLoader loader = new ResourceLoader();
        String prompt = loader.loadPromptTemplate("src/main/resources/data/prompttemplate.txt");
        return geminiService.callApi(prompt, geminiKey);
    }

}