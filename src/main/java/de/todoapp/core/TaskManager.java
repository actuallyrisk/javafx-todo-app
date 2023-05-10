package de.todoapp.core;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.LinkedList;

public class TaskManager {


    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void loadFromDB() {
        String[][] data = DBHandler.getAllTasks();
        tasks.clear();
        for (String[] task: data) {
            Task t = new Task(Integer.parseInt(task[0]), task[1], task[2], State.values()[Integer.parseInt(task[3])], Date.valueOf(task[4]), Priority.values()[Integer.parseInt(task[5])], Integer.parseInt(task[6]), task[7]);
            tasks.add(t);
        }
    }

    final Task getTaskByName(String name) {
        for (Task task: tasks) {
            if (name.equals(task.getName())) {
                return task;
            }
        }
        return null;
    }

    public final ArrayList<Task> getTasksByCategory(String category) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: tasks) {
            if (task.equals(category)) {
                result.add(task);
            }
        }
        return result;
    }

    public final ArrayList<Task> getTasksByState(State state) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getState() == state) {
                result.add(task);
            }
        }
        return result;
    }

    public void addTask(String name, String description, State state, Date dueDate, Priority priority, int points, String category) {

        int id = DBHandler.addTask(name, description, state.ordinal(), dueDate.toString(), priority.ordinal(),points, category );
        tasks.add(new Task(id, name, description, state, dueDate, priority, points, category));

    }

    public void deleteTask(Task task) {
        DBHandler.deleteTask(task.getId());
        tasks.remove(task);
    }



}
