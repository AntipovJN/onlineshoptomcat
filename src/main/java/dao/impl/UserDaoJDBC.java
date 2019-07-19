package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserDaoJDBC implements UserDao {

    private Logger logger = Logger.getLogger(UserDaoJDBC.class);

    @Override
    public void addUser(User user) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format("INSERT INTO users_test (email, password, role)"
                            + " VALUES ('%s','%s','%s')"
                    , user.getEmail(), user.getPassword(), user.getRole()));
            logger.info(String.format("Added new user with email = %s",user.getEmail()));
        } catch (SQLException e) {
            logger.error(String.format("Failed adding user with email = %s", user.getEmail()), e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new LinkedList<>();

        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users_test");
            while (resultSet.next()) {
                userList.add(new User(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")));
            }
        } catch (SQLException e) {
            logger.error("Failed getting list of users", e);
        }
        return userList;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    String.format("SELECT * FROM users_test WHERE email='%s'", email));
            resultSet.next();
            return Optional.of(new User(resultSet.getLong("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("role")));
        } catch (SQLException e) {
            logger.error(String.format("Failed getting user with email = '%s'", email), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getById(Long id) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    String.format("SELECT * FROM users_test WHERE id='%s'", id));
            resultSet.next();
            return Optional.of(new User(resultSet.getLong("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("role")));
        } catch (SQLException e) {
            logger.error(String.format("Failed getting user with id = '%s'", id), e);
        }
        return Optional.empty();
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(
                    "UPDATE users_test SET email='%s', password='%s', role='%s' WHERE id='%s'",
                    user.getEmail(), user.getPassword(), user.getRole(), user.getId()));
            logger.info(String.format("Updated user with id = %s", user.getId()));
        } catch (SQLException e) {
            logger.error(String.format("Failed updating product with id = '%s'", user.getId()), e);
        }
    }

    @Override
    public void removeUser(User user) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format(
                    "DELETE FROM users_test WHERE id='%s'", user.getId()));
            logger.info(String.format("Deleted user with id = %s", user.getId()));
        } catch (SQLException e) {
            logger.error(String.format("Failed removing product with id = '%s'", user.getId()), e);
        }
    }

}
