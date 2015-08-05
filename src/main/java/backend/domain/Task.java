package backend.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 8/1/15.
 */
public class Task implements Comparable<Task>{

    private int id;
    private String name;
    private String detailedDescription;
    private LocalDateTime creationTime;
    private final List<Task> subTasks = new ArrayList<>();
    private int priority;
    private Status status;

    public Task() {}

    public Task(String name, String detailedDescription) {
        this.name = name;
        this.detailedDescription = detailedDescription;
        this.creationTime = LocalDateTime.now();
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSubTask(Task subTask) {
        this.subTasks.add(subTask);
    }

    public List<Task> getAllSubTasks() {
        return this.subTasks;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Task o) {
        int otherTaskPriority = o.getPriority();
        return otherTaskPriority - priority;
    }
}
