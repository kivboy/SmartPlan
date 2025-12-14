package by.vadarod.smartplan.mapper;

import by.vadarod.smartplan.dto.project.ProjectCreateRequest;
import by.vadarod.smartplan.dto.project.ProjectResponse;
import by.vadarod.smartplan.dto.project.ProjectUpdateRequest;
import by.vadarod.smartplan.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toEntity(ProjectCreateRequest createRequest);
    Project toEntity(ProjectUpdateRequest updateRequest);
    ProjectResponse toResponse(Project project);
    void updateProject(@MappingTarget Project project, ProjectUpdateRequest updateRequest);
}
