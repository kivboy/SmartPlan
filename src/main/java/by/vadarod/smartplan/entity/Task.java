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
}
