package by.vadarod.smartplan.dto.task;

import by.vadarod.smartplan.entity.enums.TaskStatus;
import by.vadarod.smartplan.entity.enums.validation.ValidEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

@Data
public class TaskUpdateStatusRequest {
    private Long userId = 0L;
    @ValidEnum(enumClass = TaskStatus.class, message = "Недопустимое значение для статуса. Допустимы NEW|ASSIGNED|IN_PROCESS|CANCELLED|COMPLETED")
    private String status;

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
