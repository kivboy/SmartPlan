package by.vadarod.smartplan.services;

import by.vadarod.smartplan.dto.task.TaskCreateRequest;
import by.vadarod.smartplan.dto.task.TaskResponse;
import by.vadarod.smartplan.entity.Task;
import by.vadarod.smartplan.mapper.TaskMapper;
import by.vadarod.smartplan.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskResponse addTask(TaskCreateRequest createRequest) {
        return taskMapper.toResponse(taskRepository.save(taskMapper.toEntity(createRequest)));
    }

    @Override
    public TaskResponse getTaskById(Long taskId) {
        Optional<Task> taskOptional= taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            return taskMapper.toResponse(taskOptional.get());
        } else {
            return new TaskResponse();
        }
    }

    @Override
    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
