package by.vadarod.smartplan.services;

import by.vadarod.smartplan.dto.project.ProjectCreateRequest;
import by.vadarod.smartplan.dto.project.ProjectResponse;

public interface ProjectService {
    ProjectResponse addProject(ProjectCreateRequest createRequest);
    void deleteProjectById(Long projectId);
    ProjectResponse getProjectById(Long projectId);
}
