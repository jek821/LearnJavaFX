package todo.model;

public class Task {
    private String description;

    public Task() {
        // no arg constructor
    }

    public Task(String description){
        this.description = description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
