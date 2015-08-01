package backend.domain;

/**
 * Created by simon on 8/1/15.
 */
public class Task {

    private int id;
    private String name;

    public Task() {}

    public Task(String name) {
        this.name = name;
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
