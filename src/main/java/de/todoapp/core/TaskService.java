package de.todoapp.core;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * This class represents a task manager that allows users to manage a list of tasks.
 *
 * @author Anton Horn
 * @version 1.0
 */
public class TaskService {

    /**
     * List of tasks managed by the task service.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructor method that initializes the tasks list as an empty ArrayList.
     */
    public TaskService() {
        tasks = new ArrayList<>();
    }

    /**
     * Method to load tasks from a database.
     */
    public void loadFromDB() {
        // Retrieve all tasks from the database as a 2D array of strings
        ArrayList<String[]> data = Database.getAllTasks();
        // Clear the current list of tasks
        tasks.clear();
        // Iterate over the database data
        for (String[] task : data) {
            // Create a new Task object from each row of database data, converting values from strings to their respective data types using the State and Priority enums and the Integer.parseInt() and Date.valueOf() methods
            Task t = new Task(Integer.parseInt(task[0]), task[1], task[2], State.values()[Integer.parseInt(task[3])], Date.valueOf(task[4]), Priority.values()[Integer.parseInt(task[5])], Integer.parseInt(task[6]), task[7]);
            // Add the new task to the list of tasks
            tasks.add(t);
        }
    }

    /**
     * Retrieves a list of all tasks.
     *
     * @return a new ArrayList containing all tasks
     */
    public final ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Method to get a task by name.
     *
     * @param name the name of the task to retrieve.
     * @return the task with the specified name, or null if no task is found.
     */
    final Task getTaskByName(String name) {
        return tasks.stream().filter((task -> name.equals(task.getName()))).findFirst().get();

    }

    /**
     * Method to get a list of tasks by category.
     *
     * @param category the category of the tasks to retrieve.
     * @return an ArrayList of tasks with the specified category.
     */
    public final ArrayList<Task> getTasksByCategory(String category) {
        return new ArrayList<>(tasks.stream().filter((task -> task.getCategory().equals(category))).toList());
    }

    /**
     * Method to get a list of tasks by state.
     *
     * @param state the state of the tasks to retrieve.
     * @return an ArrayList of tasks with the specified state.
     */
    public final ArrayList<Task> getTasksByState(State state) {
        return new ArrayList<>(tasks.stream().filter((task -> task.getState() == state)).toList());
    }

    /**
     * Retrieves tasks with the specified priority.
     *
     * @param priority the priority of the tasks to retrieve
     * @return an ArrayList containing tasks with the specified priority
     */
    public final ArrayList<Task> getTaskByPriority(Priority priority) {
        return new ArrayList<>(tasks.stream().filter((task -> task.getPriority() == priority)).toList());
    }

    /**
     * Retrieves tasks with a future due date.
     *
     * @return an ArrayList containing tasks with a due date after the current date
     */
    public final ArrayList<Task> getTasksWithFutureDueDate() {
        LocalDate currentDate = LocalDate.now();

        return new ArrayList<>(tasks.stream()
                .filter(task -> {
                    LocalDate dueDate = LocalDate.parse(task.getDueDate().toString());
                    return dueDate.isAfter(currentDate);
                })
                .toList());
    }

    /**
     * Retrieves tasks due today.
     *
     * @return an ArrayList containing tasks with a due date equal to the current date
     */
    public ArrayList<Task> getTasksDueToday() {
        LocalDate currentDate = LocalDate.now();

        return new ArrayList<>(tasks.stream()
                .filter(task -> {
                    LocalDate dueDate = LocalDate.parse(task.getDueDate().toString());
                    return dueDate.isEqual(currentDate);
                })
                .toList());
    }

    /**
     * Method to add a new task.
     *
     * @param name        the name of the task to add.
     * @param description the description of the task to add.
     * @param state       the state of the task to add.
     * @param dueDate     the due date of the task to add.
     * @param priority    the priority of the task to add.
     * @param points      the number of points assigned to the task.
     * @param category    the category of the task to add.
     * @return void
     */
    public void addTask(String name, String description, State state, Date dueDate, Priority priority, int points, String category) {

        // Add the new task to the database and retrieve its ID
        int id = Database.addTask(name, description, state.ordinal(), dueDate.toString(), priority.ordinal(), points, category);
        // Create a new Task object with the specified values and the ID retrieved from the database
        tasks.add(new Task(id, name, description, state, dueDate, priority, points, category));

    }

    /**
     * Method to delete a task.
     *
     * @param task the task to delete.
     * @return void
     */
    public void deleteTask(Task task) {
        // Delete the task from the database using its ID
        Database.deleteTask(task.getId());
        // Remove the task from the tasks list
        tasks.remove(task);
    }

    public int getUserPoints() {
        return Database.getPointsById(1);
    }

    public void addUserPoints(int points) {
        Database.addPoints(1, points);
    }

}

