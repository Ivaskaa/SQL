package Hibernate.dao.forProduct;

import Hibernate.dao.DAOSession;
import Hibernate.entity.Product;
import org.hibernate.Session;


import java.util.List;

public class ProductDAOImpl extends DAOSession implements ProductDAO{
    @Override
    public List<Product> getAll() {
        openTransactionSession();
        Session session = getSession();
        List<Product> products = session.createQuery("from Product").list();
        closeTransactionSession();
        return products;
    }

    @Override
    public Product getById(int id) {
        openTransactionSession();
        Session session = getSession();
        Product product = session.get(Product.class, id);
        closeTransactionSession();
        return product;
    }

    @Override
    public void save(Product product) {
        openTransactionSession();
        Session session = getSession();
        session.save(product);
        closeTransactionSession();
    }

    @Override
    public void update(Product product) {
        openTransactionSession();
        Session session = getSession();
        session.update(product);
        closeTransactionSession();
    }

    @Override
    public void delete(Product product) {
        openTransactionSession();
        Session session = getSession();
        session.delete(product);
        closeTransactionSession();
    }
}
