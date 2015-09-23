package backend.service;

import backend.domain.Task;
import backend.persistence.TaskMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by simon on 8/1/15.
 */

@Service
public class TaskService {

    private static final Logger logger = Logger.getLogger(TaskService.class);

    @Autowired
    private TaskMapper taskMapper;

    @Transactional(readOnly = false)
    public void addTask(Task taskToAdd) { taskMapper.addTask(taskToAdd); }

    @Transactional(readOnly = true)
    public Task getTask(long taskId) { return taskMapper.getTask(taskId); }

    @Transactional(readOnly = true)
    public List<Task> findAll() { return taskMapper.getAllTasks(); }

    @Transactional(readOnly = false)
    public void deleteTask(Task task) {
        taskMapper.deleteTask(task);
    }

    @Transactional(readOnly = false)
    public void addSubTask(Task parent, Task child) throws Exception{
        if (parent.getAllSubTasks().contains(child))
            return;
        try {
            logger.debug("Adding subtask: " + child.getId() + " to parent: " + parent.getId());
            parent.addSubTask(child);
            taskMapper.addSubtask(parent, child);
        }
        catch (Exception e)
        {
            logger.debug("Removing subatask with ID " + child.getId() );
            parent.removeSubTask(child);
            throw e;
        }
    }

    @Transactional(readOnly = false)
    public void addSubTaskException(Task parent, Task child) throws IllegalArgumentException {
        parent.addSubTask(child);
        taskMapper.addSubtask(parent, child);
        throw new IllegalArgumentException();
    }

    @Transactional(readOnly = true)
    public List<Task> findAllSubtasks(Task parent) {
        logger.debug("Getting subtasks for: " + parent.getId());
        return taskMapper.getSubtasks(parent);
    }

    @Transactional(readOnly = false)
    public void updateTask(Task task) { taskMapper.updateTask(task); }
}
