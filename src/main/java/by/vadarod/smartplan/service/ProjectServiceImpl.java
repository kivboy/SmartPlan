package by.vadarod.smartplan.service;

import by.vadarod.smartplan.dto.project.ProjectCreateRequest;
import by.vadarod.smartplan.dto.project.ProjectResponse;
import by.vadarod.smartplan.dto.project.ProjectUpdateRequest;
import by.vadarod.smartplan.entity.Project;
import by.vadarod.smartplan.exception.DuplicateEntityException;
import by.vadarod.smartplan.exception.EntityNotFoundException;
import by.vadarod.smartplan.mapper.ProjectMapper;
import by.vadarod.smartplan.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        if (projectRepository.existsByKey(project.getKey())) {
            throw new DuplicateEntityException("Ключ проекта должен быть уникальным!");
        }

        return projectMapper.toResponse(projectRepository.save(project));
    }

    public ProjectResponse deleteProjectById(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            projectRepository.deleteById(projectId);
            return projectMapper.toResponse(projectOptional.get());
        } else {
            throw new EntityNotFoundException("Не найден Project по id=" + projectId);
        }
    }

    public ProjectResponse getProjectById(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            return projectMapper.toResponse(projectOptional.get());
        } else {
            throw new EntityNotFoundException("Не найден Project по id=" + projectId);
        }
    }

    @Override
    public ProjectResponse updateProject(Long projectId, ProjectUpdateRequest updateRequest) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            projectMapper.updateProject(project, updateRequest);
            return projectMapper.toResponse(projectRepository.save(project));
        } else {
            throw new EntityNotFoundException("Не найден проект по id=" + projectId);
        }
    }

    @Override
    public Page<ProjectResponse> getProjects(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Page<Project> projectsPage = projectRepository.findAll(PageRequest.of(page, size, sort));

        // преобразуем Page<Project> → Page<ProjectResponse> через MapStruct
        return projectsPage.map(projectMapper::toResponse);
    }
}
