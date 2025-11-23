package by.vadarod.smartplan.entity;

import by.vadarod.smartplan.entity.enums.TaskPriority;
import by.vadarod.smartplan.entity.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "plans", name = "tasks")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private LocalDateTime created;
    private LocalDate dueDate;
    @Column (name = "task_title", length=200, nullable = false)
    private String title;
    @Column (name = "task_description", length=1000)
    private String description;
    @Column (name = "task_status", nullable = false)
    private TaskStatus status;
    @Column (name = "task_priority", nullable = false)
    private TaskPriority priority;

    public Task(LocalDateTime created, LocalDate dueDate, String title, String description, TaskStatus status, TaskPriority priority) {
        if (created == null) {
            throw new IllegalArgumentException("Task created date must not be null!");
        }
        if ((dueDate != null) && (dueDate.isBefore(created.toLocalDate()))) {
            throw new IllegalArgumentException("Task dueDate must be after created date!");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title must not be null or blank!");
        }
        if (status == null) {
            throw new IllegalArgumentException("Task status must not be null!");
        }
        if (priority == null) {
            throw new IllegalArgumentException("Task priority must not be null!");
        }

        this.created = created;
        this.dueDate = dueDate;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }
}
