package JDBC.forDetailsUser;

import JDBC.dao.forDetailsUser.DetailsUserDAO;
import JDBC.dao.forDetailsUser.DetailsUserDAOImpl;
import JDBC.entity.DetailsUser;
import JDBC.entity.User;
import JDBC.dao.forUser.UserDAO;
import JDBC.dao.forUser.UserDAOImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import workers.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DetailsUserDAOImplTest {

    private static JDBCConnection dbWorker;
    private static User user;
    private static UserDAO userDAO;
    private static DetailsUser detailsUser;
    private static DetailsUserDAO detailsUserDAO;

    @BeforeClass
    public static void before(){
        dbWorker = new JDBCConnection();
        user = new User(1, "testLogin", "testPassword");
        userDAO = new UserDAOImpl();
        detailsUser = new DetailsUser(1, "testName", "testSurname", 1, 1);
        detailsUserDAO = new DetailsUserDAOImpl();
    }

    @Test
    public void getByIdProduct() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        detailsUser.setUser_id(user.getId());
        detailsUserDAO.save(detailsUser);
        lastDetailsUserSetting();

        DetailsUser test;
        test = detailsUserDAO.getById(detailsUser.getId());
        Assert.assertEquals(test, detailsUser);

        detailsUserDAO.delete(detailsUser.getId());
        userDAO.delete(user.getId());
    }

    @Test
    public void saveDetailsUser() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        detailsUser.setUser_id(user.getId());
        detailsUserDAO.save(detailsUser);
        lastDetailsUserSetting();

        Assert.assertEquals(detailsUserDAO.getById(detailsUser.getId()), detailsUser);

        detailsUserDAO.delete(detailsUser.getId());
        userDAO.delete(user.getId());
    }

    @Test
    public void updateDetailsUser() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        detailsUser.setUser_id(user.getId());
        detailsUserDAO.save(detailsUser);
        lastDetailsUserSetting();

        detailsUser.setName("updatedName");
        detailsUser.setSurname("updatedSurname");
        detailsUser.setAge(2);
        detailsUserDAO.update(detailsUser);
        Assert.assertEquals(detailsUserDAO.getById(detailsUser.getId()), detailsUser);

        detailsUserDAO.delete(detailsUser.getId());
        userDAO.delete(user.getId());
    }

    @Test
    public void getAllDetailsUser() throws SQLException{
        List<DetailsUser> detailsUsers;
        detailsUsers = detailsUserDAO.getAll();
        for(DetailsUser du: detailsUsers){
            Assert.assertEquals(detailsUserDAO.getById(du.getId()), du);
        }
    }

    @Test
    public void deleteDetailsUser() throws SQLException{
        userDAO.save(user);
        lastUserSetting();
        detailsUser.setUser_id(user.getId());
        detailsUserDAO.save(detailsUser);
        lastDetailsUserSetting();

        detailsUserDAO.delete(detailsUser.getId());
        userDAO.delete(user.getId());
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("UPDATE shop.user_details SET `name` = 'newName', surname = 'newSurname' WHERE id = " + detailsUser.getId());
        Assert.assertEquals(resultSet, 0);
    }

    public void lastUserSetting() throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.user ORDER BY Id DESC LIMIT 1");
        resultSet.next();
        user.setId(resultSet.getInt("id"));
    }

    public void lastDetailsUserSetting() throws SQLException{
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.user_details ORDER BY Id DESC LIMIT 1");
        resultSet.next();
        detailsUser.setId(resultSet.getInt("id"));
    }
}
