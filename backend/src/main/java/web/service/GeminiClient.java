package web.service;

import java.io.IOException;

public interface GeminiClient {
    public String getDataFromPrompt(String promptPath) throws IOException;
}
