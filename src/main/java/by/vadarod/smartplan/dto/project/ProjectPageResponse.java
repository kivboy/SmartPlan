package by.vadarod.smartplan.dto.project;

import lombok.Data;

import java.util.List;

@Data
public class ProjectPageResponse {
    private List<ProjectResponse> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
}
