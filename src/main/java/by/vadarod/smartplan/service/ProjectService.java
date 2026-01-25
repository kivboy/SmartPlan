package by.vadarod.smartplan.service;

import by.vadarod.smartplan.dto.project.ProjectCreateRequest;
import by.vadarod.smartplan.dto.project.ProjectResponse;
import by.vadarod.smartplan.dto.project.ProjectUpdateRequest;
import org.springframework.data.domain.Page;

public interface ProjectService {
    ProjectResponse addProject(ProjectCreateRequest createRequest);
    ProjectResponse deleteProjectById(Long projectId);
    ProjectResponse getProjectById(Long projectId);
    ProjectResponse updateProject(Long projectId, ProjectUpdateRequest updateRequest);
    Page<ProjectResponse> getProjects(int page, int size, String sortBy, String direction);
}
