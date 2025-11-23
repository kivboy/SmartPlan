package by.vadarod.smartplan.services;

import by.vadarod.smartplan.dto.task.TaskCreateRequest;
import by.vadarod.smartplan.dto.task.TaskResponse;

public interface TaskService {
    TaskResponse addTask(TaskCreateRequest createRequest);
    TaskResponse getTaskById(Long taskId);
    void deleteTaskById(Long taskId);
}
