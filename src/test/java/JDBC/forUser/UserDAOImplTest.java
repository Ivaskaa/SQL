package JDBC.forUser;


import JDBC.dao.forUser.UserDAO;
import JDBC.dao.forUser.UserDAOImpl;
import JDBC.entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import workers.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDAOImplTest {
    private static User user;
    private static UserDAO userDAO;
    private static JDBCConnection dbWorker;

    @BeforeClass
    public static void before(){
        user = new User(1, "testLogin", "testPassword");
        userDAO = new UserDAOImpl();
        dbWorker = new JDBCConnection();
    }

    @Test
    public void getById() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        User test;
        test = userDAO.getById(user.getId());
        Assert.assertEquals(test, user);
        userDAO.delete(user.getId());
    }

    @Test
    public void saveUser() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        Assert.assertEquals(userDAO.getById(user.getId()), user);
        userDAO.delete(user.getId());
    }

    @Test
    public void updateUser() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        user.setLogin("updatedLogin");
        user.setPassword("updatedPassword");
        userDAO.update(user);
        Assert.assertEquals(userDAO.getById(user.getId()), user);
        userDAO.delete(user.getId());
    }

    @Test
    public void getAllUser() throws SQLException{
        List<User> users;
        users = userDAO.getAll();
        for(User u: users){
            Assert.assertEquals(userDAO.getById(u.getId()), u);
        }
    }

    @Test
    public void deleteUser() throws SQLException{
        userDAO.save(user);
        lastUserSetting();
        userDAO.delete(user.getId());
        // отримати всіх і перевірити
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("UPDATE shop.user SET login = 'ok', password = 'ok' WHERE id = " + user.getId());
        Assert.assertEquals(resultSet, 0);
    }

    public void lastUserSetting() throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.user ORDER BY Id DESC LIMIT 1");
        resultSet.next();
        user.setId(resultSet.getInt("id"));
    }
}
