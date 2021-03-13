package com.company;

import java.sql.*;

public class UserDAO extends User {

    public static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM users where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE  FROM users WHERE id = ?";
    private static final String SHOW_ALL =
            "SELECT * FROM users";

    public User create(User user) {

        try (Connection conn = DbUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(User user) {
        try (Connection conn = DbUtil.connect()) {

            PreparedStatement prepStm = conn.prepareStatement(UPDATE_USER_QUERY);
            prepStm.setString(1, user.getEmail());
            prepStm.setString(2, user.getUserName());
            prepStm.setString(3, user.getPassword());
            prepStm.setInt(4, 16);
            prepStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(SHOW_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                System.out.println(resultSet.getString(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3)));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(int userId) {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement statement =
                    conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User read(int userId) {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement stmt = conn.prepareStatement(READ_USER_QUERY);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                return new User(rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());

    }
}
