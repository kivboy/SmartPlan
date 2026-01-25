package by.vadarod.smartplan.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class UserContactPageResponse {
    private List<UserContactProjection> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
}
