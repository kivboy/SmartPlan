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
    @Column (name = "file_type", length=10)
    private String fileType;
    @Column (nullable = false)
    private LocalDateTime timestamp;

    /* Закомментировано до момента начала работы с файлами
    @Lob
    @Column (name = "file_data", nullable = false)
    private byte[] fileData;
    @Column (name = "file_size", nullable = false)
    private Long fileSize;
    */

    public File(String fileName, String fileType, LocalDateTime timestamp) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("Filename must not be null or blank!");
        }
        if (timestamp == null) {
            throw new IllegalArgumentException("File created timestamp must not be null!");
        }

        this.fileName = fileName;
        this.fileType = fileType;
        this.timestamp = timestamp;
    }
}
