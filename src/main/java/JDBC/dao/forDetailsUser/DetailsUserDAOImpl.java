package JDBC.dao.forDetailsUser;

import JDBC.entity.DetailsUser;
import workers.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DetailsUserDAOImpl implements DetailsUserDAO{
    private JDBCConnection dbWorker;

    public DetailsUserDAOImpl() {
        this.dbWorker = new JDBCConnection();
    }

    public List<DetailsUser> getAll() throws SQLException {
        List<DetailsUser> detailsUsers = new ArrayList<>();
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.user_details");
        while(resultSet.next()) {
            DetailsUser user = new DetailsUser();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setAge(resultSet.getInt("age"));
            user.setUser_id(resultSet.getInt("user_id"));
            detailsUsers.add(user);
        }
        System.out.println("getAll detailsUser");
        return detailsUsers;
    }

    public DetailsUser getById(int id) throws SQLException {
        DetailsUser user = new DetailsUser();
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.user_details WHERE id = " + id);
        resultSet.next();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setAge(resultSet.getInt("age"));
        user.setUser_id(resultSet.getInt("user_id"));
        System.out.println("GetById detailsUser");
        return user;
    }

    public void save(DetailsUser user) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        // dвертання id lastinsertedid
        int resultSet = statement.executeUpdate("INSERT shop.user_details(name, surname, age, user_id) VALUES ('" +
                user.getName() + "', '" +
                user.getSurname() + "', " +
                user.getAge() + ", " +
                user.getUser_id() + ")");
        System.out.println("Added detailsUser: " + resultSet);
    }

    public void update(DetailsUser user) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("UPDATE shop.user_details SET " +
                "name = '" + user.getName() +
                "', surname = '" + user.getSurname() +
                "', age = " + user.getAge() +
                ", user_id = " + user.getUser_id() +
                " WHERE id = " + user.getId());
        System.out.println("Updated detailsUser: " + resultSet);
    }

    public void delete(int id) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("DELETE FROM shop.user_details WHERE Id = " + id);
        System.out.println("Deleted detailsUser: " + resultSet);
    }
}
