package JDBC.dao.forOrder;


import JDBC.entity.Order;
import workers.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO{
    private JDBCConnection dbWorker;

    public OrderDAOImpl() {
        this.dbWorker = new JDBCConnection();
    }

    public List<Order> getAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.order");
        while(resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setDateAndTime(resultSet.getTimestamp("date_and_time"));
            order.setProductId(resultSet.getInt("product_id"));
            order.setUserId(resultSet.getInt("user_id"));
            orders.add(order);
        }
        System.out.println("getAll order");
        return orders;
    }

    public Order getById(int id) throws SQLException {
        Order order = new Order();
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.order WHERE id = " + id);
        resultSet.next();

        order.setId(resultSet.getInt("id"));
        order.setDateAndTime(resultSet.getTimestamp("date_and_time"));
        order.setProductId(resultSet.getInt("product_id"));
        order.setUserId(resultSet.getInt("user_id"));
        System.out.println("GetById order");
        return order;
    }

    public void save(Order order) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("INSERT shop.order(date_and_time, product_id, user_id) VALUES (TIMESTAMP('" +
                order.getDateAndTime() + "'), " +
                order.getProductId() + ", " +
                order.getUserId() + ")");
        System.out.println("Saved order: " + resultSet);
    }

    public void update(Order order) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("UPDATE shop.order SET " +
                "date_and_time = TIMESTAMP('" + order.getDateAndTime() +
                "'), product_id = " + order.getProductId() +
                ", user_id = " + order.getUserId() +
                " WHERE id = " + order.getId());
        System.out.println("Updated order: " + resultSet);
    }

    public void delete(int id) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("DELETE FROM shop.order WHERE Id = " + id);
        System.out.println("Deleted order: " + resultSet);
    }
}
