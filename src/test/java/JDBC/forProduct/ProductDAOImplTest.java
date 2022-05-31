package JDBC.forProduct;


import JDBC.dao.forProduct.ProductDAO;
import JDBC.dao.forProduct.ProductDAOImpl;
import JDBC.entity.Product;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import workers.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductDAOImplTest {
    private static JDBCConnection dbWorker;
    private static Product product;
    private static ProductDAO productDAO;

    @BeforeClass
    public static void before(){
        dbWorker = new JDBCConnection();
        product = new Product(1, "testName", 1.1);
        productDAO = new ProductDAOImpl();
    }

    @Test
    public void getByIdProduct() throws SQLException {
        productDAO.save(product);
        lastProductSetting();
        Product test;
        test = productDAO.getById(product.getId());
        Assert.assertEquals(test, product);
        productDAO.delete(product.getId());
    }

    @Test
    public void saveProduct() throws SQLException {
        productDAO.save(product);
        lastProductSetting();
        Assert.assertEquals(productDAO.getById(product.getId()), product);
        productDAO.delete(product.getId());
    }

    @Test
    public void updateProduct() throws SQLException {
        productDAO.save(product);
        lastProductSetting();
        product.setName("updatedName");
        product.setPrice(2.2);
        productDAO.update(product);
        Assert.assertEquals(productDAO.getById(product.getId()), product);
        productDAO.delete(product.getId());
    }

    @Test
    public void getAllProduct() throws SQLException{
        List<Product> products;
        products = productDAO.getAll();
        for(Product p: products){
            Assert.assertEquals(productDAO.getById(p.getId()), p);
        }
    }

    @Test
    public void deleteProduct() throws SQLException{
        productDAO.save(product);
        lastProductSetting();
        productDAO.delete(product.getId());
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("UPDATE shop.product SET name = 'newName' WHERE id = " + product.getId());
        Assert.assertEquals(resultSet, 0);
    }

    public void lastProductSetting() throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.product ORDER BY Id DESC LIMIT 1");
        resultSet.next();
        product.setId(resultSet.getInt("id"));
    }
}
