package com.company;

import java.sql.*;
import java.util.Arrays;

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

    public User[] findAll() {
        User[] userLIst = new User[0];

        try (Connection conn = DbUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(SHOW_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                userLIst = Arrays.copyOf(userLIst, userLIst.length + 1);
                userLIst[userLIst.length-1] = new User(rs.getString(2), rs.getString(3), rs.getString(4));
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" " + rs.getString(3) +" "+ rs.getString(4));
            }
            return userLIst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

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
            PreparedStatement prepStm = conn.prepareStatement(UPDATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            prepStm.setString(1, user.getUserName());
            prepStm.setString(2, user.getEmail());
            prepStm.setString(3, this.hashPassword(user.getPassword()));
            prepStm.setInt(4, user.getId());
            prepStm.executeUpdate();
        } catch (SQLException e) {
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
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User readToUpdate(int userId) {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement stmt = conn.prepareStatement(READ_USER_QUERY);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getInt(1)+" "+rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
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
