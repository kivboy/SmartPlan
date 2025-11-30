package by.vadarod.smartplan.dto.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class ProjectCreateRequest {
    @NotBlank(message = "Поле name не должно быть пустым")
    @Length(max = 200, message = "Поле name не должно быть больше 200 символов")
    private String name;
    @Length(max = 1000, message = "Поле description не должно быть больше 1000 символов")
    private String description;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Поле startDate должно быть заполнено")
    private LocalDate startDate;
    private LocalDate endDate;
}
