package by.vadarod.smartplan.dto.user;

import by.vadarod.smartplan.entity.enums.UserRole;
import lombok.Data;

@Data
public class UserCreateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private String password;
}
