package by.vadarod.smartplan;

import by.vadarod.smartplan.entity.Project;
import by.vadarod.smartplan.services.ProjectService;
import by.vadarod.smartplan.services.ProjectServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@ComponentScan("by.vadarod.smartplan")
public class Main {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        ProjectService projectService = (ProjectService)context.getBean("projectServiceImpl",
                ProjectServiceImpl.class);

        Project project = new Project();
        project.setName("project1");
        project.setDescription("my first project");
        project.setStartDate(LocalDate.now());

        projectService.addProject(project);

    }
}