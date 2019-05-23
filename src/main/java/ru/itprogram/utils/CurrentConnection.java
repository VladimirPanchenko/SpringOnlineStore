package ru.itprogram.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CurrentConnection {
    private Connection connection;

    public CurrentConnection(String dbUrl, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
