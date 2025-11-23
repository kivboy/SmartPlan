package by.vadarod.smartplan.dto.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private Long id;
    private String text;
    private LocalDateTime created;
    private LocalDateTime updated;
}
