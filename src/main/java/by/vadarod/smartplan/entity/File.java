package by.vadarod.smartplan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(schema = "plans", name = "files")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "file_name", length=200, nullable = false)
    private String fileName;
    @Column (name = "file_type", length=50)
    private String fileType;
    @Column (nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    @ToString.Exclude
    private Task task;
}
