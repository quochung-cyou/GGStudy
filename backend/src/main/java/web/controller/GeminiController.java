package web.controller;

import org.springframework.beans.factory.annotation.Value;
import web.service.GeminiService;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GeminiController {

    @Autowired
    GeminiService geminiService;

    @Value("${gemini.api-key}")
    private String geminiKey;

    @GetMapping("/prompt")
    public String getResponse() {
        String prompt = "You are acting as a Teacher preparing a lesson plan. When receiving a user's request about learning a topic, provide slides that include basic information about the topic the user wants to learn, maybe including definitions, properties, syntax, etc. along with clear illustrative examples, and some other information related to that topic, then convert them into a .json file.\n" +
                "\n" +
                "There are 3 types of slides used in the slide creation process:\n" +
                "\n" +
                "Slide of a certain chapter: 1 bold line of text stating the chapter name. The chapter name needs to be given correctly to display what the chapter content is about.\n" +
                "Slide 1 image: 1 image on the left + 1 title + content text (important information should be bold or italic or highlighted)\n" +
                "Slide 2 images: 1 title above, 2 images below, each image has 1 title + 1 content below each image. Example images should be practical and easy to remember, and the content text should be as concise as possible.\n" +
                "In each slide, it should be broken down into sections including topic name (formatted as heading_title), the content inside (formatted as paragraph_text), images (1 image or 2 images). I want slides with only chapter name to be formatted as \"TEXT_ONLY\" type of slide, slides with 1 image and content to be \"ONE_IMAGE_AND_TEXT\", and 2 images to be \" TWO_IMAGES_AND_TEXT\". The .json file should be divided into chapters format along with its chapter name. Use exact, simple, concise, easy-to-understand, important content, and practical, easy-to-remember examples.\n" +
                "\n" +
                "Your main objective is: Convert the slides information into one fully complete .json file containing all chapters' information. Do not provide me a basic outline, give a full one. Remember, give me a complete .json file, do not split it into different parts.";
        return geminiService.callApi(prompt, geminiKey);
    }

}