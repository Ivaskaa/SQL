package Hibernate.dao.forOrder;

import Hibernate.entity.Order;

import java.util.List;

public interface OrderDAO {
    public List<Order> getAll();
    public Order getById(int id);
    public void save(Order order);
    public void update(Order order);
    public void delete(Order order);
}
