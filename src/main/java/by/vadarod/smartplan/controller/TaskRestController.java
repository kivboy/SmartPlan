package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.task.TaskCreateRequest;
import by.vadarod.smartplan.dto.task.TaskResponse;
import by.vadarod.smartplan.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("tasks")
@Tag(name = "Задачи", description = "Сервисы по работе с задачами")
public class TaskRestController {
    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Добавление задачи", description = "Добавление новой задачи")
    public TaskResponse addTask(@RequestBody TaskCreateRequest createRequest) {
        return taskService.addTask(createRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение задачи", description = "Получение задачи по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") Long taskId) {
        TaskResponse taskResponse = taskService.getTaskById(taskId);
        return new ResponseEntity<>(taskResponse,  HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление задачи", description = "Удаление задачи по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    public void delete(@PathVariable("id") Long taskId) {
        taskService.deleteTaskById(taskId);
    }

//    @GetMapping("/find")
//    public Collection<TaskResponse> getTaskByDate(@RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom) {
//        return taskService.findTasksByDate(dateFrom);
//    }

}
