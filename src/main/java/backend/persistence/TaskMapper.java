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

    @Delete("DELETE FROM task WHERE id = #{id}")
    public void deleteTask(Task task);

    @Update("<script>" +
            "UPDATE task " +
                "<set>" +
                    "<if test='name!=null'>name=#{name},</if>" +
                    "<if test='detailedDescription!=null'>detailedDescription=#{detailedDescription},</if>" +
                    "<if test='creationTime!=null'>creationTime=#{creationTime},</if>" +
                   "<if test='priority!=null'>priority=#{priority},</if>" +
                "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    public void updateTask(Task task);

}
