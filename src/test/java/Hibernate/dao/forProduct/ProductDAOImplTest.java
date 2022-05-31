package Hibernate.dao.forProduct;

import Hibernate.dao.forUser.UserDAO;
import Hibernate.dao.forUser.UserDAOImpl;
import Hibernate.entity.Product;
import Hibernate.entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDAOImplTest {
    private static User user;
    private static UserDAO userDAO;
    private static Product product;
    private static ProductDAO productDAO;

    @BeforeClass
    public static void before(){
        user = new User();
        user.setLogin("testLogin");
        user.setPassword("testPassword");
        product = new Product();
        product.setName("testName");
        product.setPrice(1.1);
        Set<User> users = new HashSet<>();
        users.add(user);
        product.setUsers(users);
        Set<Product> products = new HashSet<>();
        products.add(product);
        user.setProducts(products);
        userDAO = new UserDAOImpl();
        productDAO = new ProductDAOImpl();
    }

    @Test
    public void getById() {
        userDAO.save(user);
        productDAO.save(product);
        Product test = productDAO.getById(product.getId());
        Assert.assertEquals(test, product);
        productDAO.delete(product);
    }

    @Test
    public void save(){
        userDAO.save(user);
        productDAO.save(product);
        Product test = productDAO.getById(product.getId());
        Assert.assertEquals(test, product);
        productDAO.delete(product);
    }

    @Test
    public void update(){
        userDAO.save(user);
        productDAO.save(product);
        product.setName("updatedName");
        productDAO.update(product);
        Product test = productDAO.getById(product.getId());
        Assert.assertEquals(test, product);
        productDAO.delete(product);
    }

    @Test
    public void getAll(){
        List<Product> products;
        products = productDAO.getAll();
        for(Product p: products){
            Assert.assertEquals(productDAO.getById(p.getId()), p);
        }
    }

    @Test
    public void delete(){
        userDAO.save(user);
        productDAO.save(product);
        productDAO.delete(product);
        List<Product> products = productDAO.getAll();
        for(Product p: products){
            if(product.equals(p)){
                Assert.fail();
            }
        }
    }
}
