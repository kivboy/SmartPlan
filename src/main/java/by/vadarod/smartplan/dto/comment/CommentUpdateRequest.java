package by.vadarod.smartplan.dto.comment;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CommentUpdateRequest {
    @NotEmpty(message = "Поле text не заполнено")
    @Length(max = 1000, message = "Поле text превысило 1000 символов")
    private String text;
}
