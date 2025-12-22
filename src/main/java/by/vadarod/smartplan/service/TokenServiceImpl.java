package by.vadarod.smartplan.service;

import by.vadarod.smartplan.entity.RefreshToken;
import by.vadarod.smartplan.entity.User;
import by.vadarod.smartplan.exception.CustomRefreshTokenException;
import by.vadarod.smartplan.jwt.JwtService;
import by.vadarod.smartplan.jwt.model.JwtAuthenticationResponse;
import by.vadarod.smartplan.repository.RefreshTokenRepository;
import by.vadarod.smartplan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

    @Value("${jwt.refresh-token.lifeTime}")
    private Long lifeTime;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public JwtAuthenticationResponse refresh(String token) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByToken(token);
        if (optionalRefreshToken.isEmpty()) {
            throw new CustomRefreshTokenException("Ваш токен не валиден!");
        } else {
            RefreshToken refreshToken = optionalRefreshToken.get();
            if (refreshToken.getExpiresAt().isBefore(LocalDateTime.now())) {
                throw new CustomRefreshTokenException("Время жизни токена истекло!");
            }

            User user = refreshToken.getUser();
            String accessToken = jwtService.generateToken(user);
            String refreshTokenString = UUID.randomUUID().toString();
            refreshToken.setToken(refreshTokenString);
            refreshToken.setExpiresAt(LocalDateTime.now().plusMinutes(lifeTime));
            refreshTokenRepository.save(refreshToken);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setAccessToken(accessToken);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenString);

            return jwtAuthenticationResponse;
        }
    }

    @Override
    public String generateRefreshToken(String userName) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserLogin(userName);

        RefreshToken refreshToken;
        if (optionalRefreshToken.isEmpty()) {
            User user = userRepository.findByLogin(userName).orElseThrow(() ->
                    new CustomRefreshTokenException("Такого пользователя нет"));
            refreshToken = new RefreshToken();
            refreshToken.setUser(user);
        } else {
            refreshToken = optionalRefreshToken.get();
        }

        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiresAt(LocalDateTime.now().plusMinutes(lifeTime));
        refreshToken = refreshTokenRepository.save(refreshToken);

        return refreshToken.getToken();
    }
}
