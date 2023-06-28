package de.todoapp.core;

public class DatabaseLoader implements Runnable {
    @Override
    public void run() {

        Database.createDatabase();

    }
}
