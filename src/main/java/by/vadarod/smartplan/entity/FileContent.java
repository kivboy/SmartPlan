package by.vadarod.smartplan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "plans", name = "file_contents")
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = {"data", "file"}) // чтобы не печатать бинарные данные в логах
public class FileContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Связь с основной сущностью File
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", nullable = false)
    private File file;
    // Содержимое файла
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "data", nullable = false)
    private byte[] data;
}
