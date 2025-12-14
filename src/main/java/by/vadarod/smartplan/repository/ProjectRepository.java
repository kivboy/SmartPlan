package by.vadarod.smartplan.repository;

import by.vadarod.smartplan.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByKey(String key);
}
