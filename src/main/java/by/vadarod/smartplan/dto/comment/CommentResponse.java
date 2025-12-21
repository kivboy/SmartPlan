package by.vadarod.smartplan.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private Long id;
    private String text;
    private LocalDateTime created;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updated;
}
