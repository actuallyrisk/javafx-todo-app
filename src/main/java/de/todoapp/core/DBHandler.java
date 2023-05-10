package de.todoapp.core;

import java.sql.*;

public class DBHandler {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        // Create the SQLite database if it doesn't exist
        createDatabase();

    }

    private static void createDatabase() {
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.db");
            statement = connection.createStatement();

            // Create the tasks table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS tasks (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "due_date TEXT NOT NULL," +
                    "importance INTEGER NOT NULL," +
                    "finished INTEGER" +
                    ")";
            statement.execute(createTableSQL);

            // Close the statement and connection
            statement.close();
            connection.close();

            System.out.println("Database created successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void addTask(String name, String dueDate, int importance) {
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.db");
            statement = connection.createStatement();

            // Insert a new task into the database
            String insertSQL = "INSERT INTO tasks (name, due_date, importance, finished) " +
                    "VALUES ('" + name + "', '" + dueDate + "', " + importance + ", 0)";
            statement.execute(insertSQL);

            // Close the statement and connection
            statement.close();
            connection.close();

            System.out.println("Task added successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void editTask(int id, String name, String dueDate, int importance, int finished) {
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.db");
            statement = connection.createStatement();

            // Update an existing task in the database
            String updateSQL = "UPDATE tasks SET name='" + name + "', due_date='" + dueDate + "', " +
                    "importance=" + importance + ", finished=" + finished + " WHERE id=" + id;
            statement.execute(updateSQL);

            // Close the statement and connection
            statement.close();
            connection.close();

            System.out.println("Task updated successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deleteTask(int id) {
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.db");
            statement = connection.createStatement();

            // Delete an existing task from the database
            String deleteSQL = "DELETE FROM tasks WHERE id=" + id;
            statement.execute(deleteSQL);

            // Close the statement and connection
            statement.close();
            connection.close();

            System.out.println("Task deleted successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String[][] getAllTasks() {
        String[][] tasks = null;
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.db");
            statement = connection.createStatement();

            // Retrieve all tasks from the database
            String selectSQL = "SELECT * FROM tasks";
            ResultSet resultSet = statement.executeQuery(selectSQL);

            // Determine the number of tasks in the result set
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
            }
            resultSet.beforeFirst();

            // Create an array to hold the tasks
            tasks = new String[rowCount][5];

            // Fill the array with the tasks
            int i = 0;
            while (resultSet.next()) {
                tasks[i][0] = Integer.toString(resultSet.getInt("id"));
                tasks[i][1] = resultSet.getString("name");
                tasks[i][2] = resultSet.getString("due_date");
                tasks[i][3] = Integer.toString(resultSet.getInt("importance"));
                tasks[i][4] = Integer.toString(resultSet.getInt("finished"));
                i++;
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();

            System.out.println("All tasks retrieved successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tasks;
    }

    public static String[] getTaskById(int id) {
        String[] task = null;
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.db");
            statement = connection.createStatement();

            // Retrieve the task with the specified ID from the database
            String selectSQL = "SELECT * FROM tasks WHERE id=" + id;
            ResultSet resultSet = statement.executeQuery(selectSQL);

            // Fill the array with the task
            task = new String[5];
            if (resultSet.next()) {
                task[0] = Integer.toString(resultSet.getInt("id"));
                task[1] = resultSet.getString("name");
                task[2] = resultSet.getString("due_date");
                task[3] = Integer.toString(resultSet.getInt("importance"));
                task[4] = Integer.toString(resultSet.getInt("finished"));
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();

            System.out.println("Task retrieved successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return task;
    }
}
