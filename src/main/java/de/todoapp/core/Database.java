package de.todoapp.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.sql.*;

public class Database {
    private static final Logger logger = LogManager.getLogger(Database.class);
    private static Connection connection;
    private static PreparedStatement preparedStatement;

    public static void createDatabase() {
        try {
            // Check if the todo.sqlite file exists
            File dbFile = new File("todo.sqlite");
            boolean isNewDatabase = !dbFile.exists();

            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.sqlite");

            if (isNewDatabase) {
                // Create the tasks table
                String createTableSQL = "CREATE TABLE IF NOT EXISTS tasks (" +
                        "id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "description TEXT," +
                        "state INTEGER NOT NULL," +
                        "due_date DATE NOT NULL," +
                        "priority INTEGER NOT NULL," +
                        "points INTEGER NOT NULL," +
                        "category TEXT" +
                        ")";
                preparedStatement = connection.prepareStatement(createTableSQL);
                preparedStatement.execute();

                logger.info("Database created successfully.");
            } else {
                // Check if the tasks table exists
                DatabaseMetaData metadata = connection.getMetaData();
                ResultSet tables = metadata.getTables(null, null, "tasks", null);
                boolean isTasksTableExists = tables.next();
                tables.close();

                if (!isTasksTableExists) {
                    // Create the tasks table if it doesn't exist
                    String createTableSQL = "CREATE TABLE tasks (" +
                            "id INTEGER PRIMARY KEY," +
                            "name TEXT NOT NULL," +
                            "description TEXT," +
                            "state INTEGER NOT NULL," +
                            "due_date DATE NOT NULL," +
                            "priority INTEGER NOT NULL," +
                            "points INTEGER NOT NULL," +
                            "category TEXT" +
                            ")";
                    preparedStatement = connection.prepareStatement(createTableSQL);
                    preparedStatement.execute();

                    logger.info("Tasks table created successfully.");
                } else {
                    logger.info("Database already exists.");
                }
            }

            // Close the prepared statement and connection
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }



    public static int addTask(String name, String description, int state, String dueDate, int priority, int points, String category) {
        int taskId = -1;
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.sqlite");

            // Insert a new task into the database
            String insertSQL = "INSERT INTO tasks (name, description, state, due_date, priority, points, category) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, state);
            preparedStatement.setString(4, dueDate);
            preparedStatement.setInt(5, priority);
            preparedStatement.setInt(6, points);
            preparedStatement.setString(7, category);
            int rowsAffected = preparedStatement.executeUpdate();

            // Get the generated key (i.e. the ID of the inserted task)
            if (rowsAffected == 1) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    taskId = rs.getInt(1);
                }
            }

            // Close the prepared statement and connection
            preparedStatement.close();
            connection.close();

            logger.info("Task added successfully.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return taskId;
    }

    public static void editTask(int id, String column, String value) {
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.sqlite");

            // Update an existing task in the database
            String updateSQL = "UPDATE tasks SET " + column + " = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            // Close the prepared statement and connection
            preparedStatement.close();
            connection.close();

            logger.info("Task updated successfully.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public static void deleteTask(int id) {
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.sqlite");

            // Delete an existing task from the database
            String deleteSQL = "DELETE FROM tasks WHERE id = ?";
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            // Close the prepared statement and connection
            preparedStatement.close();
            connection.close();

            logger.info("Task deleted successfully.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public static String[][] getAllTasks() {
        String[][] tasks = null;
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.sqlite");

            // Retrieve all tasks from the database
            String selectSQL = "SELECT * FROM tasks";
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Determine the number of tasks in the result set
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
            }
            resultSet.beforeFirst();

            // Create an array to hold the tasks
            tasks = new String[rowCount][8];

            // Fill the array with the tasks
            int i = 0;
            while (resultSet.next()) {
                tasks[i][0] = Integer.toString(resultSet.getInt("id"));
                tasks[i][1] = resultSet.getString("name");
                tasks[i][2] = resultSet.getString("description");
                tasks[i][3] = Integer.toString(resultSet.getInt("state"));
                tasks[i][4] = resultSet.getString("due_date");
                tasks[i][5] = Integer.toString(resultSet.getInt("priority"));
                tasks[i][6] = Integer.toString(resultSet.getInt("points"));
                tasks[i][7] = resultSet.getString("category");
                i++;
            }

            // Close the result set, prepared statement, and connection
            resultSet.close();
            preparedStatement.close();
            connection.close();

            logger.info("All tasks retrieved successfully.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return tasks;
    }

    public static String[] getTaskById(int id) {
        String[] task = null;
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.sqlite");

            // Retrieve the task with the specified ID from the database
            String selectSQL = "SELECT * FROM tasks WHERE id = ?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Fill the array with the task
            task = new String[5];
            if (resultSet.next()) {
                task[0] = Integer.toString(resultSet.getInt("id"));
                task[1] = resultSet.getString("name");
                task[2] = resultSet.getString("due_date");
                task[3] = Integer.toString(resultSet.getInt("importance"));
                task[4] = Integer.toString(resultSet.getInt("finished"));
            }

            // Close the result set, prepared statement, and connection
            resultSet.close();
            preparedStatement.close();
            connection.close();

            logger.info("Task retrieved successfully.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return task;
    }
}
