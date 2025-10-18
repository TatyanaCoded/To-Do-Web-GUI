package org.example;

public class TodoItem {
    private long id;
    private String task;
    private boolean completed;

    // Constructor used by the DAO
    public TodoItem(long id, String task) {
        this.id = id;
        this.task = task;
        this.completed = false; // New items are uncompleted by default
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    // EL uses this method to access the 'completed' property
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return id + ". " + task + (completed ? " (Completed)" : "");
    }
}