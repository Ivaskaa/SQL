
import Hibernate.entity.User;
import Hibernate.dao.forUser.UserDAO;
import Hibernate.dao.forUser.UserDAOImpl;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        User user = new User();
        user.setId(1);
        user.setLogin("testLogin");
        user.setPassword("testPassword");
        UserDAO userDAO = new UserDAOImpl();
        userDAO.getAll();

        // запитати про set i equals


//        UserDAOImpl userDAO = new UserDAOImpl();
//        User user = new User(1, "login", "pas");
//        userDAO.save(user);

//        JDBCConnection worker = new JDBCConnection();
//        long before = System.currentTimeMillis();
//        Timestamp timestamp = new Timestamp(before);
//
//        User user = new User(25, "newLogin", "new");
//        UserDAO userDAO = new UserDAOImpl();
//        userDAO.update(user);
//
//        // видалення update
    }
}
