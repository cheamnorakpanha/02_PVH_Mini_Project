package org.ksga.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {

    public static Connection getConnection() throws SQLException {

        Properties properties = CredentialsLoader.loadProperties();

        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        Connection connection = DriverManager.getConnection(url, username, password);

        if (!connection.isValid(2)) {
            throw new SQLException("Database connection is not valid.");
        }

        return connection;
    }
}