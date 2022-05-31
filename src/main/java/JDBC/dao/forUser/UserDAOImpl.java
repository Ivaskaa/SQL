package JDBC.dao.forUser;

import JDBC.entity.User;
import workers.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO{
    private JDBCConnection dbWorker;

    public UserDAOImpl() {
        this.dbWorker = new JDBCConnection();
    }

    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.user");
        while(resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            users.add(user);
        }
        System.out.println("getAll user");
        return users;
    }

    public User getById(int id) throws SQLException {
        User user = new User();
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.user WHERE id = " + id);
        resultSet.next();

        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        System.out.println("GetById user");
        return user;
    }

    public void save(User user) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement(); // запитатись про id
        int resultSet = statement.executeUpdate("INSERT shop.user(login, password) VALUES ('" + user.getLogin() + "', '" + user.getPassword() + "')" );
        System.out.println("Added user: " + resultSet);
    }

    public void update(User user) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("UPDATE shop.user SET " +
                "login = '" + user.getLogin() +
                "', password = '" + user.getPassword() +
                "' WHERE id = " + user.getId());
        System.out.println("Updated user: " + resultSet);
    }

    public void delete(int id) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("DELETE FROM shop.user WHERE Id = " + id);
        System.out.println("Deleted user: " + resultSet);
    }
}
