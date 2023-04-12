package de.todoapp.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoTask implements TodoManager {
    private final DatabaseManager database;
    private static final String ADD_TODO_SQL = "INSERT INTO todo (task) VALUES (?)";
    private static final String READ_TODO_BY_ID_SQL = "SELECT * FROM todo WHERE id = ?";
    private static final String UPDATE_TODO_SQL = "UPDATE todo SET task = ?, done = ? WHERE id = ?";
    private static final String DELETE_TODO_SQL = "DELETE FROM todo WHERE id = ?";

    public TodoTask(DatabaseManager database) {
        this.database = database;
    }

    @Override
    public void addTodo(String task) {
        try (Connection conn = database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(ADD_TODO_SQL)) {

            pstmt.setString(1, task);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Todo erfolgreich hinzugefügt.");
            } else {
                System.out.println("Das Todo konnte nicht hinzugefügt werden.");
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Hinzufügen des Todos: " + e.getMessage());
        }
    }

    @Override
    public Todo readTodoById(int id) {
        Todo todo = null;
        try (Connection conn = database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(READ_TODO_BY_ID_SQL)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int todoId = rs.getInt("id");
                String task = rs.getString("task");
                boolean done = rs.getInt("done") == 1;

                todo = new Todo(todoId, task, done);
            } else {
                System.out.println("Das Todo mit ID " + id + " konnte nicht gefunden werden.");
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Lesen des Todos: " + e.getMessage());
        }
        return todo;
    }

    @Override
    public void updateTodoById(int id, String task, boolean done) {
        try (Connection conn = database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(UPDATE_TODO_SQL)) {

            pstmt.setString(1, task);
            pstmt.setInt(2, done ? 1 : 0);
            pstmt.setInt(3, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Todo erfolgreich aktualisiert.");
            } else {
                System.out.println("Das Todo mit ID " + id + " konnte nicht aktualisiert werden.");
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Aktualisieren des Todos: " + e.getMessage());
        }
    }

    @Override
    public void deleteTodoById(int id) {
        try (Connection conn = database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(DELETE_TODO_SQL)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Todo erfolgreich gelöscht.");
            } else {
                System.out.println("Das Todo mit ID " + id + " konnte nicht gefunden werden.");
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen des Todos: " + e.getMessage());
        }
    }
}
