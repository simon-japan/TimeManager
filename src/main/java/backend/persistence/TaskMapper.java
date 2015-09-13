package backend.persistence;

import org.apache.ibatis.annotations.*;

import backend.domain.Task;

import java.util.List;

/**
 * Created by simon on 8/1/15.
 */
public interface TaskMapper {
    @Select("SELECT * FROM task WHERE id = #{taskId}")
    public Task getTask(@Param("taskId") long taskId);

    @Select("SELECT * FROM task")
    public List<Task> getAllTasks();

    @Insert("INSERT INTO task (name, detailedDescription, creationTime, priority) " +
            "values(#{name}, #{detailedDescription}, #{creationTime}, #{priority})")
    @Options(useGeneratedKeys=true)
    public void addTask(Task task);

    @Insert("INSERT INTO taskSubtask (parentId, childId) values(parent.id, child.id)")
    public void addSubtask(Task parent, Task child);

    @Select("SELECT * FROM task t WHERE id in " +
            "(SELECT s.childId FROM taskSubtask s WHERE s.parentId = t.id)")
    public List<Task> getSubtasks(Task parent);

}
