package by.vadarod.smartplan.service;

import by.vadarod.smartplan.dto.task.TaskCreateRequest;
import by.vadarod.smartplan.dto.task.TaskResponse;
import by.vadarod.smartplan.dto.task.TaskUpdateStatusRequest;
import by.vadarod.smartplan.entity.Project;
import by.vadarod.smartplan.entity.Task;
import by.vadarod.smartplan.entity.User;
import by.vadarod.smartplan.entity.enums.TaskStatus;
import by.vadarod.smartplan.exception.CustomInvalidRequestException;
import by.vadarod.smartplan.exception.EntityNotFoundException;
import by.vadarod.smartplan.mapper.TaskMapper;
import by.vadarod.smartplan.repository.ProjectRepository;
import by.vadarod.smartplan.repository.TaskRepository;
import by.vadarod.smartplan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(ProjectRepository projectRepository,
                           TaskRepository taskRepository,
                           TaskMapper taskMapper,
                           UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userRepository = userRepository;
    }

    @Override
    public TaskResponse addTask(Long projectId, TaskCreateRequest createRequest) {

        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            Task task = taskMapper.toEntity(createRequest);
            task.setProject(optionalProject.get());

            if (createRequest.getUserId() > 0) {
                Optional<User> optionalUser = userRepository.findById(createRequest.getUserId());
                optionalUser.ifPresent(task::setUser);
            }

            return taskMapper.toResponse(taskRepository.save(task));
        } else {
            throw new EntityNotFoundException("Не найден проект по id=" + projectId);
        }
    }

    @Override
    public TaskResponse getTaskById(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            return taskMapper.toResponse(taskOptional.get());
        } else {
            throw new EntityNotFoundException("Не найден Task по id=" + taskId);
        }
    }

    @Override
    public TaskResponse deleteTaskById(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            taskRepository.deleteById(taskId);
            return taskMapper.toResponse(taskOptional.get());
        } else {
            throw new EntityNotFoundException("Не найден Task по id=" + taskId);
        }
    }

    @Override
    public Collection<TaskResponse> findTaskByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId).stream()
                .map(taskMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse updateTaskStatus(Long taskId, TaskUpdateStatusRequest updateStatusRequest) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();

            if (updateStatusRequest.getUserId() > 0) {
                Optional<User> optionalUser = userRepository.findById(updateStatusRequest.getUserId());
                optionalUser.ifPresent(task::setUser);
            } else if (task.getUser() == null) {
                throw new CustomInvalidRequestException("Для смены статуса задачи в ней должен быть заполнен userId");
            }

            task.setStatus( Enum.valueOf( TaskStatus.class, updateStatusRequest.getStatus()));

            return taskMapper.toResponse(taskRepository.save(task));
        } else {
            throw new EntityNotFoundException("Не найден Task по id=" + taskId);
        }
    }

    @Override
    public Collection<TaskResponse> findTaskByUserId(Long userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(taskMapper::toResponse)
                .collect(Collectors.toList());
    }
}
