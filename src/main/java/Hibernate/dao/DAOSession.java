package Hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import workers.HibernateConnection;

public class DAOSession {
    private Session session;
    private Transaction transaction;

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Session openSession(){
        HibernateConnection hibernateConnection = new HibernateConnection();
        return hibernateConnection.getSessionFactory().openSession();
    }

    public Session openTransactionSession(){
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeSession(){
        session.close();
    }

    public void closeTransactionSession(){
        transaction.commit();
        closeSession();
    }
}
