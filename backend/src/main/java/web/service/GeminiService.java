package web.service;

import java.io.IOException;

public interface GeminiService {
    public String getDataFromPrompt(String promptPath) throws IOException;
}
