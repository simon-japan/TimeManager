package backend.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import backend.domain.Task;

/**
 * Created by simon on 8/1/15.
 */
public interface TaskMapper {
    @Select("SELECT * FROM task WHERE id = #{taskId}")
    public Task getTask(int taskId);

    @Insert("INSERT INTO task (name) values(#{name})")
    public int addTask(Task task);
}
