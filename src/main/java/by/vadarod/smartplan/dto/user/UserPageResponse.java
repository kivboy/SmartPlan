package by.vadarod.smartplan.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class UserPageResponse {
    private List<UserResponse> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
}
