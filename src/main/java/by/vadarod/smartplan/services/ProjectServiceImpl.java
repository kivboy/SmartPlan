package by.vadarod.smartplan.services;

import by.vadarod.smartplan.entity.Project;
import by.vadarod.smartplan.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

//    @Autowired
//    public ProjectServiceImpl(ProjectRepository projectRepository) {
//        this.projectRepository = projectRepository;
//    }

    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    public void deleteProjectById(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public Project getProjectById(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isPresent()) {
            return project.get();
        } else {
            return new Project();
        }
    }

}
