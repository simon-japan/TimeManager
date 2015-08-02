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
    public int addTask(Task taskToAdd) { return taskMapper.addTask(taskToAdd); }

    @Transactional(readOnly = true)
    public Task getTask(int taskId) { return taskMapper.getTask(taskId); }

    @Transactional(readOnly = true)
    public List<Task> findAll() { return taskMapper.getAllTasks(); }
}
