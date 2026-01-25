package by.vadarod.smartplan.services;

import by.vadarod.smartplan.jwt.model.JwtAuthenticationResponse;

public interface TokenService {
    JwtAuthenticationResponse refresh(String token);
    String generateRefreshToken(String userName);
}
