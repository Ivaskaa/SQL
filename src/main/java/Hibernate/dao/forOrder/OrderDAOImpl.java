package Hibernate.dao.forOrder;

import Hibernate.dao.DAOSession;
import Hibernate.entity.Order;
import org.hibernate.Session;

import java.util.List;

public class OrderDAOImpl extends DAOSession implements OrderDAO{

    @Override
    public List<Order> getAll() {
        openTransactionSession();
        Session session = getSession();
        List<Order> orders = session.createQuery("from Order").list();
        closeTransactionSession();
        return orders;
    }

    @Override
    public Order getById(int id) {
        openTransactionSession();
        Session session = getSession();
        Order order = session.get(Order.class, id);
        closeTransactionSession();
        return order;
    }

    @Override
    public void save(Order order) {
        openTransactionSession();
        Session session = getSession();
        session.save(order);
        closeTransactionSession();
    }

    @Override
    public void update(Order order) {
        openTransactionSession();
        Session session = getSession();
        session.update(order);
        closeTransactionSession();
    }

    @Override
    public void delete(Order order) {
        openTransactionSession();
        Session session = getSession();
        session.delete(order);
        closeTransactionSession();
    }
}
