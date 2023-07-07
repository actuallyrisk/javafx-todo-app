package de.todoapp.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

}
