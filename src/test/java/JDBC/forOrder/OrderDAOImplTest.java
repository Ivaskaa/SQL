package JDBC.forOrder;

import JDBC.dao.forOrder.OrderDAO;
import JDBC.dao.forOrder.OrderDAOImpl;
import JDBC.entity.Order;
import JDBC.entity.Product;
import JDBC.dao.forProduct.ProductDAO;
import JDBC.dao.forProduct.ProductDAOImpl;
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
import java.sql.Timestamp;
import java.util.List;

public class OrderDAOImplTest {
    private static JDBCConnection dbWorker;
    private static User user;
    private static UserDAO userDAO;
    private static Product product;
    private static ProductDAO productDAO;
    private static Order order;
    private static OrderDAO orderDAO;

    @BeforeClass
    public static void before(){
        dbWorker = new JDBCConnection();
        user = new User(1, "testLogin", "testPassword");
        userDAO = new UserDAOImpl();
        product = new Product(1, "testName", 1.1);
        productDAO = new ProductDAOImpl();

        long before = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(before);
        order = new Order(1, timestamp, 1, 1);
        orderDAO = new OrderDAOImpl();
    }

    @Test
    public void getAllOrder() throws SQLException{
        List<Order> orders;
        orders = orderDAO.getAll();
        for(Order o: orders){
            Assert.assertEquals(orderDAO.getById(o.getId()), o);
        }
    }

    @Test
    public void getByIdOrder() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        productDAO.save(product);
        lastProductSetting();
        order.setProductId(product.getId());
        order.setUserId(user.getId());
        orderDAO.save(order);
        lastOrderSetting();

        Order test;
        test = orderDAO.getById(order.getId());
        Assert.assertEquals(test, order);

        orderDAO.delete(order.getId());
        productDAO.delete(product.getId());
        userDAO.delete(user.getId());
    }


    @Test
    public void saveOrder() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        productDAO.save(product);
        lastProductSetting();
        order.setProductId(product.getId());
        order.setUserId(user.getId());
        orderDAO.save(order);
        lastOrderSetting();

        Assert.assertEquals(orderDAO.getById(order.getId()), order);

        orderDAO.delete(order.getId());
        productDAO.delete(product.getId());
        userDAO.delete(user.getId());
    }

    @Test
    public void updateOrder() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        productDAO.save(product);
        lastProductSetting();
        order.setProductId(product.getId());
        order.setUserId(user.getId());
        orderDAO.save(order);
        lastOrderSetting();

        long before = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(before);
        order.setDateAndTime(timestamp);
        orderDAO.update(order);
        Assert.assertEquals(orderDAO.getById(order.getId()), order);

        orderDAO.delete(order.getId());
        productDAO.delete(product.getId());
        userDAO.delete(user.getId());
    }

    @Test
    public void deleteOrder() throws SQLException{
        userDAO.save(user);
        lastUserSetting();
        productDAO.save(product);
        lastProductSetting();
        order.setProductId(product.getId());
        order.setUserId(user.getId());
        orderDAO.save(order);
        lastOrderSetting();

        orderDAO.delete(order.getId());
        Statement statement = dbWorker.getConnection().createStatement();
        long before = System.currentTimeMillis();
        Timestamp newTime = new Timestamp(before);
        int resultSet = statement.executeUpdate("UPDATE shop.order SET date_and_time = TIMESTAMP('" + newTime + "') WHERE id = " + user.getId());
        Assert.assertEquals(resultSet, 0);

        productDAO.delete(product.getId());
        userDAO.delete(user.getId());
    }

    public void lastUserSetting() throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.user ORDER BY Id DESC LIMIT 1");
        resultSet.next();
        user.setId(resultSet.getInt("id"));
    }

    public void lastProductSetting() throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.product ORDER BY Id DESC LIMIT 1");
        resultSet.next();
        product.setId(resultSet.getInt("id"));
    }

    private void lastOrderSetting() throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.order ORDER BY Id DESC LIMIT 1");
        resultSet.next();
        order.setId(resultSet.getInt("id"));
    }

}
