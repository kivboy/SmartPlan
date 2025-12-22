package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.task.TaskCreateRequest;
import by.vadarod.smartplan.dto.task.TaskResponse;
import by.vadarod.smartplan.exception.model.ErrorResponse;
import by.vadarod.smartplan.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/task")
@Tag(name = "Задачи", description = "Сервисы по работе с задачами")
public class TaskRestController {
    private final TaskService taskService;

    @PostMapping("/project/{projectId}")
    @Operation(summary = "Добавление задачи", description = "Добавление новой задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = TaskResponse.class)))
    })
    public TaskResponse addTask(@PathVariable Long projectId, @RequestBody @Validated TaskCreateRequest createRequest) {
        return taskService.addTask(projectId, createRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение задачи", description = "Получение задачи по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") Long taskId) {
        TaskResponse taskResponse = taskService.getTaskById(taskId);
        return new ResponseEntity<>(taskResponse,  HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление задачи", description = "Удаление задачи по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public TaskResponse delete(@PathVariable("id") Long taskId) {
        return taskService.deleteTaskById(taskId);
    }

    @GetMapping("/project/{projectId}")
    @Operation(summary = "Поиск задач", description = "Поиск задач по id проекта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список задач проекта",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TaskResponse.class))
                    )
            )
    })
    public Collection<TaskResponse> getTasksByProjectId(@PathVariable Long projectId) {
        return taskService.findTaskByProjectId(projectId);
    }

//    @GetMapping("/find")
//    public Collection<TaskResponse> getTaskByDate(@RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom) {
//        return taskService.findTasksByDate(dateFrom);
//    }

}
