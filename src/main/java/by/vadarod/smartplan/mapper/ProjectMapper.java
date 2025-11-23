package by.vadarod.smartplan.mapper;

import by.vadarod.smartplan.dto.project.ProjectCreateRequest;
import by.vadarod.smartplan.dto.project.ProjectResponse;
import by.vadarod.smartplan.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toEntity(ProjectCreateRequest createRequest);
    ProjectResponse toResponse(Project project);
}
