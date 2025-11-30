package by.vadarod.smartplan.dto.file;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class FileCreateRequest {
    @NotBlank(message = "Поле fileName должно быть заполнено")
    @Length(max = 200, message = "Поле fileName превысило 200 символов")
    private String fileName;
    private String fileType;
}
