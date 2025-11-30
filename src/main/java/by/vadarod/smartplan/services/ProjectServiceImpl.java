package by.vadarod.smartplan.services;

import by.vadarod.smartplan.dto.project.ProjectCreateRequest;
import by.vadarod.smartplan.dto.project.ProjectResponse;
import by.vadarod.smartplan.entity.Project;
import by.vadarod.smartplan.exception.EntityNotFoundException;
import by.vadarod.smartplan.mapper.ProjectMapper;
import by.vadarod.smartplan.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public ProjectResponse addProject(ProjectCreateRequest createRequest) {
        Project project = projectMapper.toEntity(createRequest);
        return projectMapper.toResponse(projectRepository.save(project));
    }

    public void deleteProjectById(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public ProjectResponse getProjectById(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            return projectMapper.toResponse(projectOptional.get());
        } else {
            throw new EntityNotFoundException("Не найден Project по id=" + projectId);
        }
    }
}
