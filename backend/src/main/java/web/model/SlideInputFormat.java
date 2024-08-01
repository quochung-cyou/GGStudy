package web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SlideInputFormat {
    @JsonProperty("chapter_name")
    private String slideTopicName;
    @JsonProperty("slide_type")
    private String slideType;
    @JsonProperty("heading_title")
    private String headingTitle;
    @JsonProperty("paragraph_text")
    private String paragraphText;
    @JsonProperty("image")
    private String singleImageUrl;
    @JsonProperty("image_1")
    private String firstImageUrl;
    @JsonProperty("image_1_title")
    private String firstImageTitle;
    @JsonProperty("image_1_text")
    private String firstImageText;
    @JsonProperty("image_2")
    private String secondImageUrl;
    @JsonProperty("image_2_title")
    private String secondImageTitle;
    @JsonProperty("image_2_text")
    private String secondImageText;
}
