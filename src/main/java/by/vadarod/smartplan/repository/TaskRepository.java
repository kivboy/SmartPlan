package by.vadarod.smartplan.repository;

import by.vadarod.smartplan.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
