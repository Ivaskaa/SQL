package Hibernate.dao.forUser;


import Hibernate.dao.DAOSession;
import Hibernate.entity.User;
import org.hibernate.Session;

import java.util.List;

public class UserDAOImpl extends DAOSession implements UserDAO{

    @Override
    public List<User> getAll() {
        openTransactionSession();
        Session session = getSession();
        List<User> users = session.createQuery("from User").list();
        closeTransactionSession();
        return users;
    }

    @Override
    public User getById(int id) {
        openTransactionSession();
        Session session = getSession();
        User user = session.get(User.class, id);
        closeTransactionSession();
        return user;
    }

    @Override
    public void save(User user) {
        openTransactionSession();
        Session session = getSession();
        session.save(user);
        closeTransactionSession();
    }

    @Override
    public void update(User user) {
        openTransactionSession();
        Session session = getSession();
        session.update(user);
        closeTransactionSession();
    }

    @Override
    public void delete(User user) {
        openTransactionSession();
        Session session = getSession();
        session.delete(user);
        closeTransactionSession();
    }
}
