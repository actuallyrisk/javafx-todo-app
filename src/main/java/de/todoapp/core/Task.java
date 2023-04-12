package de.todoapp.core;

import java.util.Date;

public abstract class Task {

    private String name;
    private String description;
    private Priority priority;
    private Date creationTimeStamp;
    private Date deadline;
    private int points;

    public Task(String name, String description, Priority priority, Date creationTimeStamp, Date deadline, int points, int categoryFlags) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.creationTimeStamp = creationTimeStamp;
        this.deadline = deadline;
        this.points = points;
        this.categoryFlags = categoryFlags;
    }

    private State state;
    private long categoryFlags;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public final String getName() {
        return name;
    }

    public final long getCategoryFlags() {
        return categoryFlags;
    }

}
