package by.vadarod.smartplan.exception.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {

    private Integer code;
    private String message;
    private List<String> messages = new ArrayList<>();
}
