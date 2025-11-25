package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.project.ProjectCreateRequest;
import by.vadarod.smartplan.dto.project.ProjectResponse;
import by.vadarod.smartplan.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectRestController {
    private final ProjectService projectService;

    @PostMapping
    public ProjectResponse addProject(@RequestBody ProjectCreateRequest createRequest) {
        return projectService.addProject(createRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable("id") Long projectId) {
        ProjectResponse projectResponse = projectService.getProjectById(projectId);
        return new ResponseEntity<>(projectResponse,  HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long projectId) {
        projectService.deleteProjectById(projectId);
    }
}
