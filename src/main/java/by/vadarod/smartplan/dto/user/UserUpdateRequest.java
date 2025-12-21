package by.vadarod.smartplan.dto.user;

import by.vadarod.smartplan.entity.enums.UserRole;
import by.vadarod.smartplan.entity.enums.validation.ValidEnum;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserUpdateRequest {
    @NotBlank(message = "Поле firstName должно быть заполнено")
    private String firstName;
    @NotBlank(message = "Поле lastName должно быть заполнено")
    private String lastName;
    @NotEmpty(message = "Поле email должно быть заполнено")
    @Email(message = "Указан некорректный формат для поля email")
    private String email;
    @NotNull(message = "Поле role должно быть заполнено")
    @ValidEnum(enumClass = UserRole.class, message = "Недопустимое значение для роли. Допустимы USER|ADMIN")
    private String role;
    @NotEmpty(message = "Поле password должно быть заполнено")
    private String password;
}
