package by.vadarod.smartplan.repository;

import by.vadarod.smartplan.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserLogin(String login);
    Optional<RefreshToken> findByToken(String token);
}
