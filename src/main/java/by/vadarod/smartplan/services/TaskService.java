package by.vadarod.smartplan.services;

import by.vadarod.smartplan.entity.Task;

public interface TaskService {
    Task addTask(Task task);
    Task getTaskById(Long taskId);
    void deleteTaskById(Long taskId);
}
