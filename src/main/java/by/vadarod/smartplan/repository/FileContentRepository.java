package by.vadarod.smartplan.repository;

import by.vadarod.smartplan.entity.FileContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileContentRepository extends JpaRepository<FileContent, Long> {
    FileContent findByFileId(Long fileId);
}
