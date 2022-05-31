package Hibernate.dao.forProduct;

import Hibernate.entity.Product;

import java.util.List;

public interface ProductDAO {
    public List<Product> getAll();
    public Product getById(int id);
    public void save(Product product);
    public void update(Product product);
    public void delete(Product product);
}
