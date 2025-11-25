package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.task.TaskCreateRequest;
import by.vadarod.smartplan.dto.task.TaskResponse;
import by.vadarod.smartplan.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TaskRestController {
    private final TaskService taskService;

    @PostMapping
    public TaskResponse addTask(@RequestBody TaskCreateRequest createRequest) {
        return taskService.addTask(createRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") Long taskId) {
        TaskResponse taskResponse = taskService.getTaskById(taskId);
        return new ResponseEntity<>(taskResponse,  HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long taskId) {
        taskService.deleteTaskById(taskId);
    }
}
