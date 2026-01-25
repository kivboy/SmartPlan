package by.vadarod.smartplan.service;

import by.vadarod.smartplan.dto.project.ProjectCreateRequest;
import by.vadarod.smartplan.dto.project.ProjectResponse;
import by.vadarod.smartplan.dto.project.ProjectUpdateRequest;
import by.vadarod.smartplan.entity.Project;
import by.vadarod.smartplan.exception.DuplicateEntityException;
import by.vadarod.smartplan.exception.EntityNotFoundException;
import by.vadarod.smartplan.mapper.ProjectMapper;
import by.vadarod.smartplan.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    public void addProject_whenProjectIsNew() {

        ProjectCreateRequest createRequest = createProjectCreateRequest();

        initMocks(createRequest);
        when(projectRepository.existsByKey(any())).thenReturn(false);

        ProjectResponse actual = projectService.addProject(createRequest);
        ProjectResponse expected = createProjectResponse(1L);

        verify(projectRepository, times(1)).save(any());
        assertEquals(expected, actual);
    }

    @Test
    public void addProject_whenProjectKeyExist() {

        ProjectCreateRequest createRequest = createProjectCreateRequest();

        initMocks(createRequest);
        when(projectRepository.existsByKey(any())).thenReturn(true);

        Exception exception = assertThrows(DuplicateEntityException.class,
                () -> projectService.addProject(createRequest));

        verify(projectRepository, never()).save(any());
        assertEquals("Ключ проекта должен быть уникальным!", exception.getMessage());
    }

    @Test
    public void getProjectById_thenProjectReturned() {

        Long projectId = 1L;
        Project project = createProject(projectId);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectMapper.toResponse(project)).thenReturn(createProjectResponse(projectId));

        ProjectResponse actual = projectService.getProjectById(projectId);
        ProjectResponse expected = createProjectResponse(projectId);

        assertEquals(expected, actual);
    }

    @Test
    public void getProjectById_WhenProjectNotExists() {

        Long projectId = 2L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class,
                () -> projectService.getProjectById(projectId));
        assertEquals("Не найден Project по id=" + projectId, exception.getMessage());
    }

    @Test
    public void updateProject_thenProjectReturned() {
        Long projectId = 1L;
        Project project = createProject(projectId);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        doNothing().when(projectMapper).updateProject(any(),any());
        initMocks(null);

        ProjectResponse actual = projectService.updateProject(projectId, new ProjectUpdateRequest());
        ProjectResponse expected = createProjectResponse(projectId);

        assertEquals(expected, actual);
    }

    @Test
    public void updateProject_whenProjectNotExist() {
        Long projectId = 2L;

        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class,
                () -> projectService.updateProject(projectId, new ProjectUpdateRequest()));
        assertEquals("Не найден проект по id=" + projectId, exception.getMessage());
    }

    @Test
    public void getProjects_thenPageProjectResponseReturned() {

        int page = 0;
        int size = 5;
        String sortBy = "id";
        String direction = "asc";

        Sort sort = Sort.by(sortBy).ascending();

        Project project1 = createProject(1L);
        Project project2 = createProject(2L);

        ProjectResponse response1 = createProjectResponse(1L);
        ProjectResponse response2 = createProjectResponse(2L);

        List<Project> projects = Arrays.asList(project1, project2);
        List<ProjectResponse> projectResponseList = Arrays.asList(response1, response2);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Project> mockedProjects = new PageImpl<>(projects, pageable, projects.size());
        when(projectRepository.findAll(any(Pageable.class))).thenReturn(mockedProjects);
        when(projectMapper.toResponse(project1)).thenReturn(response1);
        when(projectMapper.toResponse(project2)).thenReturn(response2);

        Page<ProjectResponse> actual = projectService.getProjects(page, size, sortBy, direction);

        assertEquals(projectResponseList.size(), actual.getTotalElements());
        assertEquals(1, actual.getTotalPages());
        assertEquals(projectResponseList, actual.getContent());
    }

    private void initMocks(ProjectCreateRequest createRequest) {
        if (createRequest != null) {
            when(projectMapper.toEntity(eq(createRequest))).thenReturn(new Project());
        }
        lenient().when(projectRepository.save(any())).thenReturn(new Project());
        lenient().when(projectMapper.toResponse(any())).thenReturn(createProjectResponse(1L));
    }

    private ProjectCreateRequest createProjectCreateRequest() {
        ProjectCreateRequest projectCreateRequest = new ProjectCreateRequest();
        projectCreateRequest.setKey("TEST");
        projectCreateRequest.setName("Тестовый проект");
        projectCreateRequest.setStartDate(LocalDate.now().plusDays(1L));
        return projectCreateRequest;
    }

    private ProjectResponse createProjectResponse(Long projectId) {
        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setId(projectId);
        projectResponse.setKey("TEST" + projectId);
        return projectResponse;
    }

    private Project createProject(Long projectId) {
        Project project = new Project();
        project.setId(projectId);
        return project;
    }
}
