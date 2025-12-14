package by.vadarod.smartplan.dto.user;

import by.vadarod.smartplan.entity.enums.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private String login;

}

/*  Try to use:

public record UserResponse(
        Long id,
        String username,
        String email) {
}

    Не пугайтесь, если увидели record впервые: это встроенное ключевое слово в Java,
    которое позволяет создать неизменяемый класс с final полями
    (мы не можем изменить поля после создания объекта),
    а также автоматически сгенерированными hashcode()&equals() + toString() + get() методами.
 */