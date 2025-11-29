package by.vadarod.smartplan.dto.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectCreateRequest {
    private String name;
    private String description;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private LocalDate endDate;
}
