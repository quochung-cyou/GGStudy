package web.common.io;

import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class ResourceLoader {

    private StringBuilder promptString;

    public ResourceLoader(){
        promptString = new StringBuilder();
    }

    public String loadPromptTemplate(String path){
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            promptString.setLength(0);
            String line;
            while ((line = reader.readLine()) != null){
                promptString.append(line).append("\n");
            }
            return promptString.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
