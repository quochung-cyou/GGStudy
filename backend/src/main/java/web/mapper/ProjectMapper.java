package web.mapper;

import org.mapstruct.Mapper;
import web.common.dto.ProjectDTO;
import web.model.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO toDTO(Project project);
}