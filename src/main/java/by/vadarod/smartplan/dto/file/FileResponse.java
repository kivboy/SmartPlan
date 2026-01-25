package by.vadarod.smartplan.dto.file;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileResponse {
    private Long id;
    private String fileName;
    private String fileType;
    private LocalDateTime timestamp;
    private Long taskId;
}
