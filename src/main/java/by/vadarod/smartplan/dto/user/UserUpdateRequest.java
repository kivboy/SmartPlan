package by.vadarod.smartplan.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserUpdateRequest extends UserCreateRequest {
    @NotNull(message = "Поле id не заполнено")
    private Long id;
}
