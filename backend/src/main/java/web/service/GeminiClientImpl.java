package web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeminiClientImpl implements GeminiClient {

    private final ObjectMapper objectMapper;

    @Value("${gemini.api-key}")
    private String geminiKey;

    public String getDataFromPrompt(String prompt) {

        String API_URL_TEMPLATE = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=%s";
        String apiUrl = String.format(API_URL_TEMPLATE, geminiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(makeBodyRequest(objectMapper, prompt));
        } catch (Exception e) {
            throw new RuntimeException("Failed to construct JSON request body", e);
        }

        log.info(requestBody);
        WebClient webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return webClient.post()
                .uri(apiUrl)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


    private ObjectNode makeBodyRequest(ObjectMapper objectMapper, String prompt) {
        ObjectNode contentNode = objectMapper.createObjectNode();
        ObjectNode partsNode = objectMapper.createObjectNode();
        partsNode.put("text", prompt);
        contentNode.set("parts", objectMapper.createArrayNode().add(partsNode));
        ObjectNode requestBodyNode = objectMapper.createObjectNode();
        requestBodyNode.set("contents", objectMapper.createArrayNode().add(contentNode));

        ObjectNode generationConfig = objectMapper.createObjectNode();
        generationConfig.put("response_mime_type", "application/json");
        generationConfig.put("temperature", 0.7);
        requestBodyNode.set("generationConfig", generationConfig);

        ObjectNode safetySetting = objectMapper.createObjectNode();
        safetySetting.put("category", "HARM_CATEGORY_DANGEROUS_CONTENT");
        safetySetting.put("threshold", "BLOCK_ONLY_HIGH");

        requestBodyNode.set("safetySettings", objectMapper.createArrayNode().add(safetySetting));
        return requestBodyNode;
    }
}