package de.todoapp.core;

public interface TodoManager {
    void addTodo(String task);
    void readTodoById(int id);
    void updateTodoById(int id, String task, boolean done);
    void deleteTodoById(int id);
}

