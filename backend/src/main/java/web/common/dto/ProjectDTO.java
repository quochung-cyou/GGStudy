package web.common.dto;

import lombok.Getter;
import lombok.Setter;
import web.common.shared.BaseMetadata;

@Getter
@Setter
public class ProjectDTO extends BaseMetadata {
    private String id;
    private String title;
    private int lastSeenSlide;
}
