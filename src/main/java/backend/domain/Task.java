package backend.domain;

import java.time.LocalDateTime;

/**
 * Created by simon on 8/1/15.
 */
public class Task {

    private int id;
    private String name;
    private String detailedDescription;
    private LocalDateTime creationTime;

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

}
