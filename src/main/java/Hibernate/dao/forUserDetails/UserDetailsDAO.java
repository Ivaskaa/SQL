package Hibernate.dao.forUserDetails;

import Hibernate.entity.UserDetails;

import java.util.List;

public interface UserDetailsDAO {
    public List<UserDetails> getAll();
    public UserDetails getById(int id);
    public void save(UserDetails userDetails);
    public void update(UserDetails userDetails);
    public void delete(UserDetails userDetails);
}
