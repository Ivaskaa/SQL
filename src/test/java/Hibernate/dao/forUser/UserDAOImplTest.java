package Hibernate.dao.forUser;

import Hibernate.entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class UserDAOImplTest {

    private static User user;
    private static UserDAO userDAO;

    @BeforeClass
    public static void before(){
        user = new User();
        user.setLogin("testLogin");
        user.setPassword("testPassword");
        userDAO = new UserDAOImpl();
    }

    @Test
    public void getById() {
        userDAO.save(user);
        User test = userDAO.getById(user.getId());
        Assert.assertEquals(test, user);
        userDAO.delete(user);
    }

    @Test
    public void save() {
        userDAO.save(user);
        User test = userDAO.getById(user.getId());
        Assert.assertEquals(test, user);
        userDAO.delete(user);
    }

    @Test
    public void update(){
        userDAO.save(user);
        user.setLogin("updatedLogin");
        user.setPassword("updatedPassword");
        userDAO.update(user);
        User test = userDAO.getById(user.getId());
        Assert.assertEquals(test, user);
        userDAO.delete(user);
    }

    @Test
    public void getAll(){
        List<User> users;
        users = userDAO.getAll();
        for(User u: users){
            Assert.assertEquals(userDAO.getById(u.getId()), u);
        }
    }

    @Test
    public void delete(){
        userDAO.save(user);
        userDAO.delete(user);
        List<User> users = userDAO.getAll();
        for(User u: users){
            if(user.equals(u)){
                Assert.fail();
            }
        }
    }
}
