package by.vadarod.smartplan.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {

    private Integer code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> messages = new ArrayList<>();
}
