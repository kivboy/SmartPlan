package by.vadarod.smartplan.service;

import by.vadarod.smartplan.dto.task.TaskCreateRequest;
import by.vadarod.smartplan.dto.task.TaskResponse;
import by.vadarod.smartplan.dto.task.TaskUpdateStatusRequest;

import java.util.Collection;

public interface TaskService {
    TaskResponse addTask(Long projectId, TaskCreateRequest createRequest);
    TaskResponse getTaskById(Long taskId);
    TaskResponse deleteTaskById(Long taskId);
    Collection<TaskResponse> findTaskByProjectId(Long projectId);
    TaskResponse updateTaskStatus(Long taskId, TaskUpdateStatusRequest updateStatusRequest);
    Collection<TaskResponse> findTaskByUserId(Long userId);
}
