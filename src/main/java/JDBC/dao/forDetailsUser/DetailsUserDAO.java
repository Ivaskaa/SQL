package JDBC.dao.forDetailsUser;

import JDBC.entity.DetailsUser;

import java.sql.SQLException;
import java.util.List;

public interface DetailsUserDAO {
    public List<DetailsUser> getAll() throws SQLException;
    public DetailsUser getById(int id) throws SQLException;
    public void save(DetailsUser user) throws SQLException;
    public void update(DetailsUser user) throws SQLException;
    public void delete(int id) throws SQLException;
}
