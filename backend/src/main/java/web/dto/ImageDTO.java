package web.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import web.model.Image;

@Getter
@Setter
public class ImageDTO {
    private String id;

    private String link;

    public ImageDTO(Image image){
        BeanUtils.copyProperties(image, this);
    }
}
