package de.todoapp.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.sql.*;

public class DBHandler {
    private static final Logger logger = LogManager.getLogger(DBHandler.class);
    private static Connection connection;
    private static Statement statement;

    public static void createDatabase() {
        try {
            // Check if the todo.db file exists
            File dbFile = new File("todo.db");
            boolean isNewDatabase = !dbFile.exists();

            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.db");
            statement = connection.createStatement();

            if (isNewDatabase) {
                // Create the tasks table
                String createTableSQL = "CREATE TABLE tasks (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "description TEXT," +
                        "state INTEGER NOT NULL," +
                        "due_date DATE NOT NULL," +
                        "priority INTEGER NOT NULL," +
                        "points INTEGER NOT NULL," +
                        "category TEXT" +
                        ")";
                statement.execute(createTableSQL);

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
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "name TEXT NOT NULL," +
                            "description TEXT," +
                            "state INTEGER NOT NULL," +
                            "due_date DATE NOT NULL," +
                            "priority INTEGER NOT NULL," +
                            "points INTEGER NOT NULL," +
                            "category TEXT" +
                            ")";
                    statement.execute(createTableSQL);

                    logger.info("Tasks table created successfully.");
                } else {
                    logger.info("Database already exists.");
                }
            }

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public static int addTask(String name, String description, int state, String dueDate, int priority, int points, String category) {
        int taskId = -1;
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:todo.db");
            statement = connection.createStatement();

            // Insert a new task into the database
            String insertSQL = "INSERT INTO tasks (name, description, state, due_date, priority, points, category) " +
                    "VALUES ('" + name + "', '" + description + "', '" + state + "', '" + dueDate + "', '" + priority + "', " + points + ", '" + category + "')";
            int rowsAffected = statement.executeUpdate(insertSQL, Statement.RETURN_GENERATED_KEYS);

            // Get the generated key (i.e. the ID of the inserted task)
            if (rowsAffected == 1) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    taskId = rs.getInt(1);
                }
            }

            // Close the statement and connection
            statement.close();
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
            connection = DriverManager.getConnection("jdbc:sqlite:todo.db");
            statement = connection.createStatement();

            // Update an existing task in the database
            String updateSQL = "UPDATE tasks SET " + column + "=" + value + " WHERE id=" + id;
            statement.execute(updateSQL);

            // Close the statement and connection
            statement.close();
            connection.close();

            logger.info("Task updated successfully.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
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

            logger.info("Task deleted successfully.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
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

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
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

            logger.info("Task retrieved successfully.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return task;
    }
}
