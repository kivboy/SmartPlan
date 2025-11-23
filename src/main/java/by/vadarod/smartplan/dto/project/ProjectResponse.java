package by.vadarod.smartplan.dto.project;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
