package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.project.ProjectCreateRequest;
import by.vadarod.smartplan.dto.project.ProjectResponse;
import by.vadarod.smartplan.services.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// http://localhost:8080/swagger-ui/index.html
// http://localhost:8080/v3/api-docs.yaml

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/projects")
@Tag(name = "Проекты", description = "Сервисы по работе с проектами")
public class ProjectRestController {
    private final ProjectService projectService;

    @PostMapping
    @Operation(summary = "Добавление проекта", description = "Добавление нового проекта")
    public ProjectResponse addProject(@RequestBody @Validated ProjectCreateRequest createRequest) {
        return projectService.addProject(createRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение проекта", description = "Получение проекта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Project not found", content = @Content)
    })
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable("id") Long projectId) {
        ProjectResponse projectResponse = projectService.getProjectById(projectId);
        return new ResponseEntity<>(projectResponse,  HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление проекта", description = "Удаление проекта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Project not found", content = @Content)
    })
    public void delete(@PathVariable("id") Long projectId) {
        projectService.deleteProjectById(projectId);
    }
}
