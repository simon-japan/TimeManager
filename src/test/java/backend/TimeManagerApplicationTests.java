package backend;

/**
 * Created by simon on 8/1/15.
 */

import backend.domain.Task;
import backend.service.TaskService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration

public class TimeManagerApplicationTests {

    @Autowired
    TaskService taskService;

    private Task parent;
    private Task child;

    @Before
    public void setUp()
    {
        parent = new Task("Parent task", "stuff");
        taskService.addTask(parent);
        child = new Task("Child task", "other stuff");
        taskService.addTask(child);
    }

    @After
    public void tearDown() {
        taskService.deleteTask(child);
        taskService.deleteTask(parent);
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void insertAndSelectTask_validQuery_retrievesTask() throws Exception
    {
        long taskId = parent.getId();
        System.out.println("Getting Task with id: " + taskId);
        Task foundTask = taskService.getTask(taskId);
        System.out.printf("Got task id: %d\n", taskId);
        System.out.println("Got Task: " + foundTask.getName());
        Assert.assertEquals("Task name not the same",
                foundTask.getName(), "Parent task");
    }

    @Test
    public void updateTask_validUpdate_doesUpdate() {
        long taskId = parent.getId();
        System.out.println("Updating Task");
        parent.setDetailedDescription("Description 2");
        taskService.updateTask(parent);
        System.out.println("Finished updating task in DB");
        Task updatedTaskFromDB = taskService.getTask(taskId);
        Assert.assertEquals("DetailedDescription not updated in DB", updatedTaskFromDB.getDetailedDescription(),
                "Description 2");
    }

    @Test
    public void addSubtask_validChild_addsSuccessfully()
    {
        try {
            taskService.addSubTask(parent, child);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals("The subtasks list of the domain object was not updated.",
                parent.getAllSubTasks().size(), 1);
        List<Task> subtasks = taskService.findAllSubtasks(parent);
        Assert.assertEquals("The subtasks list in the DB was not updated.", subtasks.size(), 1);
    }

    @Test
    public void addSubtask_nullChild_transactionCancelled()
    {
        try {
            taskService.addSubTask(parent, null);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals("The subtasks list of the domain object was updated.",
                parent.getAllSubTasks().size(), 0);
        List<Task> subtasks = taskService.findAllSubtasks(parent);
        Assert.assertEquals("The subtasks list in the DB was updated.", subtasks.size(), 0);
    }

    @Test
    public void addSubtask_invalidChild_rollsBackTransaction()
    {
        try {
            taskService.addSubTaskException(parent, child);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Could not add subtask. The transaction will be rolled back.");
        }
        List<Task> subtasksFromDB = taskService.findAllSubtasks(parent);
        Assert.assertEquals("DB query should not return any subtasks", subtasksFromDB.size(), 0);
        Assert.assertEquals("The domain object should not have any subtasks", parent.getAllSubTasks().size(), 0);
    }

}
