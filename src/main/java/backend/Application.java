package backend;

import backend.domain.Task;
import backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application implements CommandLineRunner{

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
/*        System.out.println("Adding Task");
        int taskId = taskService.addTask(new Task("Do stuff", "Something or other"));
        taskService.addTask(new Task("Do the other stuff", "yeah that thing"));
        System.out.println("Getting Task");
        Task task = taskService.getTask(taskId);
        System.out.println("Got Task: " + task.getName());*/
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostConstruct
    public void extension() {
        templateEngine.addDialect(new Java8TimeDialect());
    }

}
