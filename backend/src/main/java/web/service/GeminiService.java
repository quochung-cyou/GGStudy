package web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
public class GeminiService {

    @Autowired
    private ObjectMapper objectMapper;

    public String callApi(String prompt, String geminiKey){
        String API_URL_TEMPLATE = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-pro-latest:generateContent?key=%s";
        String apiUrl = String.format(API_URL_TEMPLATE, geminiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(makeBodyRequest(objectMapper, prompt));
        } catch (Exception e) {
            throw new RuntimeException("Failed to construct JSON request body", e);
        }

        WebClient webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        String reponse = webClient.post()
                .uri(apiUrl)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return formatString(reponse);
    }

    private String formatString(String jsonString){
        try {
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode text = rootNode.path("candidates").get(0).path("content").path("parts").get(0);
            StringBuilder str = new StringBuilder(text.path("text").asText());
            String json = "```json\n";
            str.delete(0, json.length());
            String endJson = "\n```\n";
            str.delete(str.length() - endJson.length(), str.length());
            System.out.println(str);
            return str.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ObjectNode makeBodyRequest(ObjectMapper objectMapper, String prompt){
        ObjectNode contentNode = objectMapper.createObjectNode();
        ObjectNode partsNode = objectMapper.createObjectNode();
        partsNode.put("text", prompt);
        contentNode.set("parts", objectMapper.createArrayNode().add(partsNode));
        ObjectNode requestBodyNode = objectMapper.createObjectNode();
        requestBodyNode.set("contents", objectMapper.createArrayNode().add(contentNode));
        return requestBodyNode;
    }
}