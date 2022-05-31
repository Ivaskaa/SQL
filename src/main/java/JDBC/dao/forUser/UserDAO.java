package JDBC.dao.forUser;

import JDBC.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public List<User> getAll() throws SQLException;
    public User getById(int id) throws SQLException;
    public void save(User user) throws SQLException;
    public void update(User user) throws SQLException;
    public void delete(int id) throws SQLException;
}
