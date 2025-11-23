package by.vadarod.smartplan.dto.task;

import by.vadarod.smartplan.entity.enums.TaskPriority;
import by.vadarod.smartplan.entity.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskResponse {
    private Long id;
    private LocalDate dueDate;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
}
