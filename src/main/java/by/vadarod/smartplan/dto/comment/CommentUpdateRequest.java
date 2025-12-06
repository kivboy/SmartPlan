package by.vadarod.smartplan.dto.comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CommentUpdateRequest {
    @NotNull(message = "Поле id не заполнено")
    private Long id;
    @NotEmpty(message = "Поле text не заполнено")
    @Length(max = 1000, message = "Поле text превысило 1000 символов")
    private String text;
}
