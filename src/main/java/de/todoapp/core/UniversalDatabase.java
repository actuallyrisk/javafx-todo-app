package de.todoapp.core.db;

import java.sql.*;

interface DatabaseManager {
    void connect() throws SQLException;
    void disconnect() throws SQLException;
}

public class UniversalDatabase implements DatabaseManager {
    protected Connection conn;
    private final String url;
    private final String user;
    private final String password;

    public UniversalDatabase(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void connect() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Datenbank-Verbindung hergestellt.");
    }

    @Override
    public void disconnect() throws SQLException {
        conn.close();
        System.out.println("Datenbank-Verbindung geschlossen.");
    }
}

