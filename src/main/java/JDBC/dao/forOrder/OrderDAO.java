package JDBC.dao.forOrder;

import JDBC.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    public List<Order> getAll() throws SQLException;
    public Order getById(int id) throws SQLException;
    public void save(Order order) throws SQLException;
    public void update(Order order) throws SQLException;
    public void delete(int id) throws SQLException;
}
