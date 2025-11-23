package by.vadarod.smartplan.repository;

import by.vadarod.smartplan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
