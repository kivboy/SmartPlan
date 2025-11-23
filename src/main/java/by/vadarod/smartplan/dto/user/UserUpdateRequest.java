package by.vadarod.smartplan.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserUpdateRequest extends UserCreateRequest {
    private Long id;
}
