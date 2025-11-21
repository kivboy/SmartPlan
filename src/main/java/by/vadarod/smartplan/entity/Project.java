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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectSeq")
    @SequenceGenerator(name = "projectSeq", schema = "plans", sequenceName = "project_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
