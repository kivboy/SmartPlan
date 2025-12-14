package by.vadarod.smartplan.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class ProjectUpdateRequest{
    @NotBlank(message = "Поле name не должно быть пустым")
    @Length(max = 200, message = "Поле name не должно быть больше 200 символов")
    private String name;
    @Length(max = 1000, message = "Поле description не должно быть больше 1000 символов")
    private String description;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Поле startDate должно быть заполнено")
    private LocalDate startDate;
    private LocalDate endDate;

    @Schema(hidden = true)
    @AssertTrue(message = "Дата startDate не должна быть в прошлом")
    public boolean isStartDateAfterCreated() {
        return startDate.isAfter(LocalDate.now());
    }

    @Schema(hidden = true)
    @AssertTrue(message = "Дата endDate должна быть после startDate")
    public boolean isEndDateAfterStartDate() {
        return (endDate == null) || endDate.isAfter(startDate);
    }
}
