package by.vadarod.smartplan.dto.user;

import by.vadarod.smartplan.entity.enums.UserRole;
import by.vadarod.smartplan.entity.enums.validation.ValidEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserCreateRequest {
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
    @NotBlank(message = "Поле login должно быть заполнено")
    @Schema(
            description = "Логин пользователя. Допустимы только латинские буквы, цифры и символы _-",
            example = "user_name123"
    )
    @Pattern(
            regexp = "^[a-zA-Z0-9_-]+$",
            message = "Логин может содержать только латинские буквы, цифры, дефис и нижнее подчеркивание"
    )
    @Size(
            min = 3,
            max = 50,
            message = "Длина логина должна быть от 3 до 50 символов"
    )
    private String login;
    @NotEmpty(message = "Поле password должно быть заполнено")
    private String password;
    private boolean enabled = true;

}
