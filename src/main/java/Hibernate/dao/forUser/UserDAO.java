package Hibernate.dao.forUser;

import Hibernate.entity.User;
import java.util.List;

public interface UserDAO {
    public List<Hibernate.entity.User> getAll();
    public User getById(int id);
    public void save(User user);
    public void update(User user);
    public void delete(User user);
}
