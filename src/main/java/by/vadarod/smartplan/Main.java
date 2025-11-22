package by.vadarod.smartplan;

import by.vadarod.smartplan.entity.*;
import by.vadarod.smartplan.entity.enums.TaskPriority;
import by.vadarod.smartplan.entity.enums.TaskStatus;
import by.vadarod.smartplan.entity.enums.UserRole;
import by.vadarod.smartplan.services.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@ComponentScan("by.vadarod.smartplan")
public class Main {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        UserService userService = context.getBean("userServiceImpl", UserServiceImpl.class);
        ProjectService projectService = context.getBean("projectServiceImpl", ProjectServiceImpl.class);
        TaskService taskService = context.getBean("taskServiceImpl", TaskServiceImpl.class);
        CommentService commentService = context.getBean("commentServiceImpl", CommentServiceImpl.class);
        FileService fileService = context.getBean("fileServiceImpl", FileServiceImpl.class);

        initDefaultUsers(userService);
        System.out.println(userService.getUserById(2L));
        userService.deleteUserById(2L);

        initDefaultProjects(projectService);
        System.out.println(projectService.getProjectById(2L));
        projectService.deleteProjectById(2L);

        initDefaultTasks(taskService);
        System.out.println(taskService.getTaskById(2L));
        taskService.deleteTaskById(2L);

        initDefaultComments(commentService);
        System.out.println(commentService.findCommentById(2L));
        commentService.deleteCommentById(2L);

        initDefaultFiles(fileService);
        System.out.println(fileService.findFileById(2L));
        fileService.deleteFileById(2L);
    }

    private static void initDefaultProjects(ProjectService projectService) {
        Project[] projects = new Project[]{
                new Project("Project1", "This is my first project", LocalDate.of(2025, 12, 1), LocalDate.of(2025, 12,31)),
                new Project("Project2", "This is my second project", LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1,30)),
                new Project("Project3", "This is my third project", LocalDate.of(2026, 2, 1), LocalDate.of(2026, 12,31))
        };
        for (Project project:projects) {
            projectService.addProject(project);
        }
    }

    private static void initDefaultTasks(TaskService taskService) {
        Task[] tasks = new Task[]{
                new Task(LocalDateTime.now(), LocalDate.of(2025,12, 1), "Task1", "Simple description", TaskStatus.NEW, TaskPriority.MEDIUM),
                new Task(LocalDateTime.now(), null, "Task2", null, TaskStatus.NEW, TaskPriority.LOW),
                new Task(LocalDateTime.now(), LocalDate.of(2026,1, 1), "Task3", "My task description", TaskStatus.IN_PROCESS, TaskPriority.HIGH)
        };
        for (Task task:tasks) {
            taskService.addTask(task);
        }
    }

    private static void initDefaultComments(CommentService commentService) {
        Comment[] comments = new Comment[] {
          new Comment("My first comment", LocalDateTime.now()),
          new Comment("My second comment", LocalDateTime.now()),
          new Comment("My third comment", LocalDateTime.now())
        };
        for (Comment comment:comments) {
            commentService.addComment(comment);
        }
    }

    private static void initDefaultFiles(FileService fileService) {
        File[] files = new File[] {
                new File("testfile1.doc","", LocalDateTime.now()),
                new File("testfile2.doc","", LocalDateTime.now()),
                new File("testfile3.doc","", LocalDateTime.now())
        };
        for (File file:files) {
            fileService.addFile(file);
        }
    }

    private static void initDefaultUsers(UserService userService) {
        User[] users = new User[] {
                new User("Иван","Иванов","ivanov@test.com", UserRole.USER),
                new User("Петр","Петров","petrov@test.com", UserRole.USER),
                new User("Дмитрий","Сергеев","dmitry@test.com", UserRole.ADMIN)
        };
        for (User user:users) {
            userService.addUser(user);
        }
    }
}