package by.vadarod.smartplan.repository;

import by.vadarod.smartplan.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
