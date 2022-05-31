package workers;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateConnection {
    private SessionFactory sessionFactory;

    public HibernateConnection(){
        try{
            sessionFactory = new Configuration()
                    .configure(new File("src\\main\\resources\\hibernate.cfg.xml"))
                    .buildSessionFactory();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public void close() {
        getSessionFactory().close();
    }
}
