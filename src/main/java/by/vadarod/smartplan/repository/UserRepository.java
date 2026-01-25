package by.vadarod.smartplan.repository;

import by.vadarod.smartplan.dto.user.UserContactProjection;
import by.vadarod.smartplan.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLogin(String login);
    Optional<User> findByLogin(String login);

    Page<UserContactProjection> findByFirstNameLikeAndLastNameLikeAndEnabledTrue(String firstNamePattern, String lastNamePattern, Pageable pageable);
}
