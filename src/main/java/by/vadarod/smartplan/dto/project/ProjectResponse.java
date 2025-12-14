package by.vadarod.smartplan.dto.project;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectResponse {
    private Long id;
    @Pattern(regexp = "^[A-Z]{2,10}$")
    private String key;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
