package com.company;

import java.sql.*;

public class DbUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dao_db?serverTimezone=Europe/Warsaw";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "coderslab";

    public static Connection connect() throws SQLException {
        return connect(DB_URL, DB_USER, DB_PASS);

    }

    public static Connection connect(String url, String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public static void printData(Connection conn, String query, String... columnNames) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                for (String param : columnNames) {
                    System.out.println(resultSet.getString(param));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
