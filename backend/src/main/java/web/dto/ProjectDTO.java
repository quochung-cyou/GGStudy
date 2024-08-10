package web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import web.model.Project;

import java.sql.Timestamp;

@Getter
@Setter
public class ProjectDTO {
    private String id;
    private String title;
    private String createBy;
    private String modifyBy;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private int lastSeenSlide;
    public ProjectDTO(Project project) {
        BeanUtils.copyProperties(project, this);
    }
}
