package backend;

import backend.domain.Task;
import backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner{

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @Autowired
    private TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Adding Task");
        int taskId = taskService.addTask(new Task("Do stuff"));
        System.out.println("Getting Task");
        Task task = taskService.getTask(taskId);
        System.out.println("Got Task: " + task.getName());
    }

}
