package com.example.sosknop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/challenge";
    private static final String USER = "user";
    private static final String PASSWORD = "ic-piP3412";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish a database connection", e);
        }
    }

}
