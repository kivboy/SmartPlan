package by.vadarod.smartplan.repository;

import by.vadarod.smartplan.entity.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void checkSaveAndExistsByKey() {
        projectRepository.save(getProject("TEST","Test project"));
        assertTrue(projectRepository.existsByKey("TEST"));
    }

    private Project getProject(String key, String name) {
        Project project = new Project();
        project.setKey(key);
        project.setName(name);
        project.setStartDate(LocalDate.now());
        return project;
    }
}
