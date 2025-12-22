package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.project.ProjectCreateRequest;
import by.vadarod.smartplan.dto.project.ProjectPageResponse;
import by.vadarod.smartplan.dto.project.ProjectResponse;
import by.vadarod.smartplan.dto.project.ProjectUpdateRequest;
import by.vadarod.smartplan.exception.model.ErrorResponse;
import by.vadarod.smartplan.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// http://localhost:8080/swagger-ui/index.html
// http://localhost:8080/v3/api-docs.yaml

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/project")
@Tag(name = "Проекты", description = "Сервисы по работе с проектами")
public class ProjectRestController {
    private final ProjectService projectService;

    @PostMapping
    @Operation(summary = "Добавление проекта", description = "Добавление нового проекта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ProjectResponse.class))),
            @ApiResponse(responseCode = "409", description = "Duplicate project key",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ProjectResponse addProject(@RequestBody @Validated ProjectCreateRequest createRequest) {
        return projectService.addProject(createRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение проекта", description = "Получение проекта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ProjectResponse.class))),
            @ApiResponse(responseCode = "404", description = "Project not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable("id") Long projectId) {
        ProjectResponse projectResponse = projectService.getProjectById(projectId);
        return new ResponseEntity<>(projectResponse,  HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление проекта", description = "Редактирование проекта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ProjectResponse.class))),
            @ApiResponse(responseCode = "404", description = "Project not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ProjectResponse updateProject(@PathVariable("id") Long projectId, @RequestBody @Validated ProjectUpdateRequest updateRequest) {
        return projectService.updateProject(projectId, updateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление проекта", description = "Удаление проекта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ProjectResponse.class))),
            @ApiResponse(responseCode = "404", description = "Project not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ProjectResponse delete(@PathVariable("id") Long projectId) {
        return projectService.deleteProjectById(projectId);
    }

    @GetMapping("/list")
    @Operation(summary = "Список проектов", description = "Получение списка проектов постранично с сортировкой по выбранному полю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ProjectPageResponse.class)))
    })
    public Page<ProjectResponse> getProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return projectService.getProjects(page, size, sortBy, direction);
    }
}
