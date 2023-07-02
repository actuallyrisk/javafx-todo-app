package de.todoapp.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {

    private static final String TEST_DB_NAME = "test.db";

    @BeforeEach
    public void setup() {
        // Create a new test database
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + TEST_DB_NAME);
            Statement statement = connection.createStatement();
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
            statement.execute(createTableSQL);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Failed to create the test database.");
        }
    }

    @Test
    public void testAddTask() throws SQLException {
        // Generate random test data
        String name = "Task 1";
        String description = "Description 1";
        int state = 0;
        String dueDate = "2023-07-05";
        int priority = 1;
        int points = 10;
        String category = "Category 1";

        // Call the method under test
        int taskId = Database.addTask(name, description, state, dueDate, priority, points, category);

        // Assert the result
        // Verify the correctness of the generated task ID or any other expected behavior
        assertEquals(1, taskId);
    }

    @Test
    public void testEditTask() throws SQLException {
        // Generate random test data
        int taskId = 1;
        String column = "name";
        String value = "Updated Task";

        // Call the method under test
        Database.editTask(taskId, column, value);

        // Assert the result
        // Verify the correctness of the update operation or any other expected behavior
        // You can retrieve the task and check if the specified column has been updated with the expected value
    }

    @Test
    public void testDeleteTask() throws SQLException {
        // Generate random test data
        int taskId = 1;

        // Call the method under test
        Database.deleteTask(taskId);

        // Assert the result
        // Verify the correctness of the delete operation or any other expected behavior
        // You can try to retrieve the deleted task and check if it no longer exists in the database
    }

    @Test
    public void testGetAllTasks() throws SQLException {
        // Generate random test data
        Database.addTask("Task 1", "Description 1", 0, "2023-07-05", 1, 10, "Category 1");
        Database.addTask("Task 2", "Description 2", 1, "2023-07-06", 2, 20, "Category 2");

        // Call the method under test
        ArrayList<String[]> tasks = Database.getAllTasks();

        // Assert the result
        // Verify the correctness of the retrieved tasks or any other expected behavior
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0)[1]);
        assertEquals("Task 2", tasks.get(1)[1]);
    }
}
