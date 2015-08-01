package backend.service;

import backend.domain.Task;
import backend.persistence.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by simon on 8/1/15.
 */

@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    public int addTask(Task taskToAdd) { return taskMapper.addTask(taskToAdd); }

    public Task getTask(int taskId) { return taskMapper.getTask(taskId); }
}
