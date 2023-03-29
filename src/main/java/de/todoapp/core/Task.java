package de.todoapp.core;

import java.util.Date;

public abstract class Task {

    private String name;
    private String description;
    private Priority priority;
    private Date creationTimeStamp;
    private Date deadline;
    private int points;

    public Task(String name, String description, Priority priority, Date creationTimeStamp, Date deadline, int points, Category category) {

        this.name = name;
        this.description = description;
        this.priority = priority;
        this.creationTimeStamp = creationTimeStamp;
        this.deadline = deadline;
        this.points = points;
        this.category = category;
        
    }

    private State state;
    private Category category;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
