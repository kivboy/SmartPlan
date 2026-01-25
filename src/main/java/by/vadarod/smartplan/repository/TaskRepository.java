package by.vadarod.smartplan.repository;

import by.vadarod.smartplan.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
    List<Task> findByUserId(Long userId);
}
