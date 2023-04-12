package de.todoapp.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.LinkedList;

public class TaskManager {


    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void load(Task[] tasks) {
        this.tasks = new ArrayList<Task>(Arrays.asList(tasks));
    }

    final Task getTaskByName(String name) {
        for (Task task: tasks) {
            if (name.equals(task.getName())) {
                return task;
            }
        }
        return null;
    }

    public final ArrayList<Task> getTasksByCategoryFlags(long flags) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: tasks) {
            if ((task.getCategoryFlags() & flags) != 0) {
                result.add(task);
            }
        }
        return result;
    }

    public final ArrayList<Task> getTasksByCategoryState(State state) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getState() == state) {
                result.add(task);
            }
        }
        return result;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }



}
