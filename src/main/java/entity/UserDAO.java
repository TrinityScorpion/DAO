package entity;

import com.company.BCrypt;
import com.company.DbUtil;
import com.company.User;

import java.sql.*;

public class UserDAO extends User {

    public static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY =
            "READ FROM users(username, email, password) VALUES (?, ?, ?)";
    private static final String UPDATE_USER_QUERY =
            "UPDATE FROM users(username, email, password) VALUES (?, ?, ?)";
    private static final String DELETE_USER_QUERY =
            "REMOVE FROM users(username, email, password) VALUES (?, ?, ?)";
    public  User create(User user) {

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
    public static String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());

    }
}
