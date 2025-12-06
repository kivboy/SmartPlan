package by.vadarod.smartplan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(schema = "plans", name = "projects")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "project_name", length=200, nullable = false)
    private String name;
    @Column (name = "project_description", length=1000)
    private String description;
    @Column (name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column (name = "end_date")
    private LocalDate endDate;
    /*
    public Project(String name, String description, LocalDate startDate, LocalDate endDate) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Project name must not be null or blank!");
        }
        if (startDate == null) {
            throw new IllegalArgumentException("Project startDate must not be null!");
        }

        if ((endDate != null) && (endDate.isBefore(startDate))) {
            throw new IllegalArgumentException("Project endDate must be after startDate!");
        }

        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }*/
}
