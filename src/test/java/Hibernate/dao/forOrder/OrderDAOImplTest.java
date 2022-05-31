package Hibernate.dao.forOrder;

import Hibernate.dao.forProduct.ProductDAO;
import Hibernate.dao.forProduct.ProductDAOImpl;
import Hibernate.dao.forUser.UserDAO;
import Hibernate.dao.forUser.UserDAOImpl;
import Hibernate.entity.Order;
import Hibernate.entity.Product;
import Hibernate.entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderDAOImplTest {
    private static User user;
    private static UserDAO userDAO;
    private static Product product;
    private static ProductDAO productDAO;
    private static Order order;
    private static OrderDAO orderDAO;

    @BeforeClass
    public static void before(){
        user = new User();
        user.setLogin("testLogin");
        user.setPassword("testPassword");
        product = new Product();
        product.setName("testName");
        product.setPrice(1.1);
        Set<User> users = new HashSet<>();
        users.add(user);
        product.setUsers(users);
        Set<Product> products = new HashSet<>();
        products.add(product);
        user.setProducts(products);
        order = new Order();
        long before = System.currentTimeMillis();
        Date date = new Timestamp(before);
        order.setDateAndTime(date);
        order.setProducts(products);
        order.setUsers(users);
        userDAO = new UserDAOImpl();
        productDAO = new ProductDAOImpl();
        orderDAO = new OrderDAOImpl();
    }

    @Test
    public void getById() {
        userDAO.save(user);
        productDAO.save(product);
        orderDAO.save(order);
        Order test = orderDAO.getById(order.getId());
        Assert.assertEquals(test, order);
        orderDAO.delete(order);
    }
    // equals set
    // sql
}
