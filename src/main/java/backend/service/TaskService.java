package backend.service;

import backend.domain.Task;
import backend.persistence.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by simon on 8/1/15.
 */

@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Transactional(readOnly = false)
    public void addTask(Task taskToAdd) { taskMapper.addTask(taskToAdd); }

    @Transactional(readOnly = true)
    public Task getTask(long taskId) { return taskMapper.getTask(taskId); }

    @Transactional(readOnly = true)
    public List<Task> findAll() { return taskMapper.getAllTasks(); }

    @Transactional(readOnly = false)
    public void deleteTask(Task task) { taskMapper.deleteTask(task); }

    @Transactional(readOnly = false)
    public void addSubTask(Task parent, Task child) { taskMapper.addSubtask(parent, child); }

    @Transactional(readOnly = true)
    public List<Task> findAllSubtasks(Task parent) { return taskMapper.getSubtasks(parent); }

    @Transactional(readOnly = false)
    public void updateTask(Task task) { taskMapper.updateTask(task); }
}
