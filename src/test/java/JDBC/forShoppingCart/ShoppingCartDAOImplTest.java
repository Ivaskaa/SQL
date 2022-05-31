package JDBC.forShoppingCart;


import JDBC.dao.forShoppingCart.ShoppingCartDAO;
import JDBC.dao.forShoppingCart.ShoppingCartDAOImpl;
import JDBC.entity.Product;
import JDBC.entity.ShoppingCart;
import JDBC.dao.forProduct.ProductDAO;
import JDBC.dao.forProduct.ProductDAOImpl;
import JDBC.entity.User;
import JDBC.dao.forUser.UserDAO;
import JDBC.dao.forUser.UserDAOImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import workers.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ShoppingCartDAOImplTest {
    private static JDBCConnection dbWorker;
    private static User user;
    private static UserDAO userDAO;
    private static Product product;
    private static ProductDAO productDAO;
    private static ShoppingCart shoppingCart;
    private static ShoppingCartDAO shoppingCartDAO;

    @BeforeClass
    public static void before(){
        dbWorker = new JDBCConnection();
        user = new User(1, "testLogin", "testPassword");
        userDAO = new UserDAOImpl();
        product = new Product(1, "testName", 1.1);
        productDAO = new ProductDAOImpl();
        shoppingCart = new ShoppingCart(1, 1);
        shoppingCartDAO = new ShoppingCartDAOImpl();
    }

    @Test
    public void getAllShoppingCart() throws SQLException {
        List<ShoppingCart> shoppingCarts;
        shoppingCarts = shoppingCartDAO.getAll();
        for(ShoppingCart sc: shoppingCarts){
            Assert.assertEquals(shoppingCartDAO.getByUserIdAndProductId(sc.getUserId(), sc.getProductId()), sc);
        }
    }

    @Test
    public void getAllByUserIdShoppingCart() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        productDAO.save(product);
        lastProductSetting();
        shoppingCart.setProductId(product.getId());
        shoppingCart.setUserId(user.getId());
        shoppingCartDAO.save(shoppingCart);

        List<ShoppingCart> shoppingCarts;
        shoppingCarts = shoppingCartDAO.getAllByUserId(shoppingCart.getUserId());
        for(ShoppingCart sc : shoppingCarts){
            Assert.assertEquals(sc, shoppingCart);
        }

        shoppingCartDAO.deleteProductFromUserShoppingCart(shoppingCart.getUserId(), shoppingCart.getProductId());
        productDAO.delete(product.getId());
        userDAO.delete(user.getId());
    }

    @Test
    public void getAllByProductIdShoppingCart() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        productDAO.save(product);
        lastProductSetting();
        shoppingCart.setProductId(product.getId());
        shoppingCart.setUserId(user.getId());
        shoppingCartDAO.save(shoppingCart);

        List<ShoppingCart> shoppingCarts;
        shoppingCarts = shoppingCartDAO.getAllByProductId(shoppingCart.getProductId());
        for(ShoppingCart sc : shoppingCarts){
            Assert.assertEquals(sc, shoppingCart);
        }

        shoppingCartDAO.deleteProductFromUserShoppingCart(shoppingCart.getUserId(), shoppingCart.getProductId());
        productDAO.delete(product.getId());
        userDAO.delete(user.getId());
    }

    @Test
    public void getAllByUserAndProductIdShoppingCart() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        productDAO.save(product);
        lastProductSetting();
        shoppingCart.setProductId(product.getId());
        shoppingCart.setUserId(user.getId());
        shoppingCartDAO.save(shoppingCart);

        ShoppingCart test;
        test = shoppingCartDAO.getByUserIdAndProductId(shoppingCart.getUserId(), shoppingCart.getProductId());
        Assert.assertEquals(shoppingCart, test);

        shoppingCartDAO.deleteProductFromUserShoppingCart(shoppingCart.getUserId(), shoppingCart.getProductId());
        productDAO.delete(product.getId());
        userDAO.delete(user.getId());
    }

    @Test
    public void saveShoppingCart() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        productDAO.save(product);
        lastProductSetting();
        shoppingCart.setProductId(product.getId());
        shoppingCart.setUserId(user.getId());
        shoppingCartDAO.save(shoppingCart);

        Assert.assertEquals(shoppingCartDAO.getByUserIdAndProductId(shoppingCart.getUserId(), shoppingCart.getProductId()), shoppingCart);

        shoppingCartDAO.deleteProductFromUserShoppingCart(shoppingCart.getUserId(), shoppingCart.getProductId());
        productDAO.delete(product.getId());
        userDAO.delete(user.getId());
    }

    @Test
    public void deleteShoppingCart() throws SQLException {
        userDAO.save(user);
        lastUserSetting();
        productDAO.save(product);
        lastProductSetting();
        shoppingCart.setProductId(product.getId());
        shoppingCart.setUserId(user.getId());
        shoppingCartDAO.save(shoppingCart);

        shoppingCartDAO.deleteProductFromUserShoppingCart(shoppingCart.getUserId(), shoppingCart.getProductId());
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("UPDATE shop.shopping_cart SET user_id = 1, product_id = 1 WHERE user_id = " + shoppingCart.getUserId() + " AND product_id = " + shoppingCart.getProductId());
        Assert.assertEquals(resultSet, 0);

        productDAO.delete(product.getId());
        userDAO.delete(user.getId());
    }

    public void lastUserSetting() throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.user ORDER BY Id DESC LIMIT 1");
        resultSet.next();
        user.setId(resultSet.getInt("id"));
    }

    public void lastProductSetting() throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.product ORDER BY Id DESC LIMIT 1");
        resultSet.next();
        product.setId(resultSet.getInt("id"));
    }
}
