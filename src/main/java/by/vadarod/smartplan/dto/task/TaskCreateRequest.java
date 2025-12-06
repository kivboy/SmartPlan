package by.vadarod.smartplan.dto.task;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class TaskCreateRequest {
    private LocalDate dueDate;
    @NotBlank(message = "Поле title не должно быть пустым")
    private String title;
    @Length(max= 1000, message = "Поле description не должно быть больше 1000 символов")
    private String description;
    @NotNull(message = "Поле status должно быть заполнено")
    @Pattern(regexp = "NEW|ASSIGNED|IN_PROCESS|CANCELLED|COMPLETED", message = "Недопустимое значение для статуса")
    private String status;
    @NotNull(message = "Поле priority должно быть заполнено")
    @Pattern(regexp = "LOW|MEDIUM|HIGH", message = "Недопустимое значение для приоритета")
    private String priority;

    @AssertTrue(message = "Дата dueDate не должна быть в прошлом")
    public boolean isDueDateAfterCreated() {
        return dueDate.isAfter(LocalDate.now());
    }
}
