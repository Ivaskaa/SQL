package Hibernate.dao.forUserDetails;

import Hibernate.dao.forUser.UserDAO;
import Hibernate.dao.forUser.UserDAOImpl;
import Hibernate.entity.User;
import Hibernate.entity.UserDetails;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class UserDetailsDAOImplTest {
    private static User user;
    private static UserDAO userDAO;
    private static UserDetails userDetails;
    private static UserDetailsDAO userDetailsDAO;

    @BeforeClass
    public static void before(){
        user = new User();
        user.setLogin("testLogin");
        user.setPassword("testPassword");
        userDAO = new UserDAOImpl();
        userDetails = new UserDetails();
        userDetails.setUser(user);
        userDetails.setAge(1);
        userDetails.setName("testName");
        userDetails.setSurname("testSurname");
        userDetailsDAO = new UserDetailsDAOImpl();
    }

    @Test
    public void getById() {
        userDAO.save(user);
        User test = userDAO.getById(user.getId());
        Assert.assertEquals(test, user);        // equals переоприділений
        userDAO.delete(user);
    }

    @Test
    public void save() {
        userDAO.save(user);
        userDetailsDAO.save(userDetails);
        UserDetails test = userDetailsDAO.getById(userDetails.getId());
        Assert.assertEquals(test, userDetails);
        userDetailsDAO.delete(userDetails);
        //userDAO.delete(user); не треба, бо user є в userDetails (user ссилочна)
    }

    @Test
    public void update(){
        userDAO.save(user);
        userDetailsDAO.save(userDetails);
        userDetails.setSurname("updatedSurname");
        userDetails.setName("updatedName");
        userDetailsDAO.update(userDetails);
        UserDetails test = userDetailsDAO.getById(userDetails.getId());
        Assert.assertEquals(test, userDetails);
        userDetailsDAO.delete(userDetails);
    }

    @Test
    public void getAll(){
        List<UserDetails> users;
        users = userDetailsDAO.getAll();
        for(UserDetails ud: users){
            Assert.assertEquals(userDetailsDAO.getById(ud.getId()), ud);
        }
    }

    @Test
    public void delete(){
        userDAO.save(user);
        userDetailsDAO.save(userDetails);
        userDetailsDAO.delete(userDetails);
        List<UserDetails> users = userDetailsDAO.getAll();
        for(UserDetails ud: users){
            if(userDetails.equals(ud)){
                Assert.fail();
            }
        }
    }
}
