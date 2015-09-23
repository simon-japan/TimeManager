package backend;

import backend.domain.Task;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by simon on 8/5/15.
 */
public class TaskTest {

    private Task myTask;

    @Before
    public void setUp() {
        myTask = new Task();
    }

    @After
    public void tearDown() {
        myTask = null;
    }

    @Test
    public void getAllSubTasks_hasNoSubtasks_returnsEmptyList() {
        List<Task> subTasks = myTask.getAllSubTasks();
        Assert.assertNotNull(subTasks);
        Assert.assertEquals(subTasks.size(), 0);
    }

}
