package by.vadarod.smartplan.jwt.model;

import lombok.Data;

@Data
public class JwtAuthenticationRequest {
    private String login;
    private String password;
}
