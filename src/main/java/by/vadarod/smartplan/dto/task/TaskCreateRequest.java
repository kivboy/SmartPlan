package by.vadarod.smartplan.dto.task;

import by.vadarod.smartplan.entity.enums.TaskPriority;
import by.vadarod.smartplan.entity.enums.validation.ValidEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class TaskCreateRequest {
    private Long userId = 0L;
    private LocalDate dueDate;
    @NotBlank(message = "Поле title не должно быть пустым")
    private String title;
    @Length(max= 1000, message = "Поле description не должно быть больше 1000 символов")
    private String description;
    @NotNull(message = "Поле status должно быть заполнено")
    //@ValidEnum(enumClass = TaskStatus.class, message = "Недопустимое значение для статуса. Допустимы NEW|ASSIGNED|IN_PROCESS|CANCELLED|COMPLETED")
    @Pattern(regexp = "NEW|ASSIGNED", message = "Недопустимое значение для статуса. Допустимы значения NEW|ASSIGNED")
    private String status;
    @NotNull(message = "Поле priority должно быть заполнено")
    @ValidEnum(enumClass = TaskPriority.class, message = "Недопустимое значение для приоритета. Допустимы LOW|MEDIUM|HIGH")
    private String priority;

    @Schema(hidden = true)
    @AssertTrue(message = "Дата dueDate не должна быть в прошлом")
    public boolean isDueDateAfterCreated() {
        return dueDate.isAfter(LocalDate.now());
    }

    @Schema(hidden = true)
    @AssertTrue(message = "При статусе ASSIGNED должен быть назначен исполнитель")
    public boolean isAssignedTo() {
        if ("ASSIGNED".equals(status)) {
            return userId > 0L;
        } else {
            return true;
        }
    }
}
