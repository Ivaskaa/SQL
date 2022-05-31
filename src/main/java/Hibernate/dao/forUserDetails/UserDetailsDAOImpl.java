package Hibernate.dao.forUserDetails;

import Hibernate.dao.DAOSession;
import Hibernate.entity.UserDetails;
import org.hibernate.Session;

import java.util.List;

public class UserDetailsDAOImpl extends DAOSession implements UserDetailsDAO{
    @Override
    public List<UserDetails> getAll() {
        openTransactionSession();
        Session session = getSession();
        List<UserDetails> userDetails = session.createQuery("from UserDetails").list();
        closeTransactionSession();
        return userDetails;
    }

    @Override
    public UserDetails getById(int id) {
        openTransactionSession();
        Session session = getSession();
        UserDetails userDetails = session.get(UserDetails.class, id);
        closeTransactionSession();
        return userDetails;
    }

    @Override
    public void save(UserDetails userDetails) {
        openTransactionSession();
        Session session = getSession();
        session.save(userDetails);
        closeTransactionSession();
    }

    @Override
    public void update(UserDetails userDetails) {
        openTransactionSession();
        Session session = getSession();
        session.update(userDetails);
        closeTransactionSession();
    }

    @Override
    public void delete(UserDetails userDetails) {
        openTransactionSession();
        Session session = getSession();
        session.delete(userDetails);
        closeTransactionSession();
    }
}
