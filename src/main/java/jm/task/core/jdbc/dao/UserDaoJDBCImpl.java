package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String createTable = "CREATE TABLE IF NOT EXISTS users" +
            "(id BIGSERIAL PRIMARY KEY, " +
            "name TEXT, " +
            "lastName TEXT," +
            "age INTEGER)";
    private static final String dropTable = "DROP TABLE IF EXISTS users";
    private static final String saveUser = "INSERT INTO users (name, lastname, age) Values (?, ?, ?)";
    private static final String userId = "SELECT * FROM users WHERE id = ?";
    private static final String allUser = "SELECT * FROM users";
    private static final String clean = "DELETE FROM users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(PreparedStatement statement = Util.getConnectionDataBase().prepareStatement(createTable)) {
            statement.execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void dropUsersTable() {
        try(PreparedStatement statement = Util.getConnectionDataBase().prepareStatement(dropTable)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement statement = Util.getConnectionDataBase().prepareStatement(saveUser)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try(PreparedStatement statement = Util.getConnectionDataBase().prepareStatement(userId)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try(PreparedStatement statement = Util.getConnectionDataBase().prepareStatement(allUser)) {
           ResultSet resultSet = statement.executeQuery();
           while(resultSet.next()) {
               User user = new User();
               user.setId(resultSet.getLong(1));
               user.setName(resultSet.getString(2));
               user.setLastName(resultSet.getString(3));
               user.setAge(resultSet.getByte(4));
               users.add(user);
           }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        try(PreparedStatement statement = Util.getConnectionDataBase().prepareStatement(clean)) {
            statement.execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}
