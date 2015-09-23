package backend;

/**
 * Created by simon on 8/1/15.
 */

import backend.domain.Task;
import backend.service.TaskService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration

public class TimeManagerApplicationTests {

    @Autowired
    TaskService taskService;

    @Test
    public void contextLoads() {

    }

    @Test
    public void insertAndSelectTask_validQuery_retrievesTask() throws Exception
    {
        System.out.println("Adding Task");
        Task task = new Task("Task 1", "Something or other");
        taskService.addTask(task);
        long taskId = task.getId();
        System.out.println("Getting Task");
        Task foundTask = taskService.getTask(taskId);
        System.out.printf("Got task id: %d\n", taskId);
        System.out.println("Got Task: " + foundTask.getName());
        Assert.assertEquals("Task name not the same",
                foundTask.getName(), "Task 1");
        taskService.deleteTask(task);
    }

    @Test
    public void updateTask_validUpdate_doesUpdate() {
        Task task = new Task("My Task", "Description 1");
        taskService.addTask(task);
        long taskId = task.getId();
        System.out.println("Updating Task");
        task.setDetailedDescription("Description 2");
        taskService.updateTask(task);
        System.out.println("Finished updating task in DB");
        Task updatedTaskFromDB = taskService.getTask(taskId);
        Assert.assertEquals("DetailedDescription not updated in DB", updatedTaskFromDB.getDetailedDescription(),
                "Description 2");
    }

}
