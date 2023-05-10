package de.todoapp.core;

import java.util.Date;

public class Task {

    private int id;
    private String name;
    private String description;
    private String category;
    private Priority priority;
    private State state;
    private Date dueDate;
    private int points;

    public Task(int id, String name, String description, State state, Date dueDate, Priority priority, int points,String category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.state = state;
        this.dueDate = dueDate;
        this.points = points;
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public final String getName() {
        return name;
    }

    public final String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public final String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getPoints() {
        return points;
    }
}
