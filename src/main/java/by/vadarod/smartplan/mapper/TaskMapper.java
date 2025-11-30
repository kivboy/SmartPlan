package by.vadarod.smartplan.mapper;

import by.vadarod.smartplan.dto.task.TaskCreateRequest;
import by.vadarod.smartplan.dto.task.TaskResponse;
import by.vadarod.smartplan.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface TaskMapper {
    @Mapping(target = "created", expression = "java(LocalDateTime.now())")
    Task toEntity(TaskCreateRequest createRequest);
    TaskResponse toResponse(Task task);
}
