package by.vadarod.smartplan.services;

import by.vadarod.smartplan.entity.Project;

public interface ProjectService {
    Project addProject(Project project);
    void deleteProject(Project project);
    void deleteProjectById(Long projectId);
    Project getProjectById(Long projectId);
}
