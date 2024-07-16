package com.example.pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlserver://localhost;databaseName=PharmacyDB";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initialize() {
        String createDrugsTable = "CREATE TABLE IF NOT EXISTS Drugs ("
                + "id INT PRIMARY KEY IDENTITY(1,1),"
                + "name VARCHAR(255),"
                + "manufacturer VARCHAR(255),"
                + "price DECIMAL(10, 2),"
                + "quantity INT"
                + ")";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createDrugsTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
