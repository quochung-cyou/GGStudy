package web.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SlideInputFormat {
    @JsonProperty("chapter_name")
    private String slideTopicName = "";
    @JsonProperty("slide_type")
    private String slideType = "";
    @JsonProperty("topic_name")
    private String topicName = "";
    @JsonProperty("heading_title")
    private String headingTitle = "Untitled";
    @JsonProperty("paragraph_text")
    private String paragraphText = "";
    @JsonProperty("image")
    private String singleImageUrl = "";
    @JsonProperty("image_1")
    private String firstImageUrl = "";
    @JsonProperty("image_1_title")
    private String firstImageTitle = "";
    @JsonProperty("image_1_text")
    private String firstImageText = "";
    @JsonProperty("image_2")
    private String secondImageUrl = "";
    @JsonProperty("image_2_title")
    private String secondImageTitle = "";
    @JsonProperty("image_2_text")
    private String secondImageText = "";
    @JsonProperty("image_3")
    private String thirdImageUrl = "";
    @JsonProperty("image_3_title")
    private String thirdImageTitle = "";
    @JsonProperty("image_3_text")
    private String thirdImageText = "";
    @JsonProperty("title_1")
    private String firstCompareTitle = "Similarities";
    @JsonProperty("title_2")
    private String secondCompareTitle = "Differences";
    @JsonProperty("differences")
    private List<DifferenceIdeaFormat> differences;
}
