package web.common.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResourceLoader {

    public ResourceLoader(){}

    public String loadPromptTemplate(String path){
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            StringBuilder promptString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                promptString.append(line).append("\n");
            }
            return promptString.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "HELLO";
    }
}
