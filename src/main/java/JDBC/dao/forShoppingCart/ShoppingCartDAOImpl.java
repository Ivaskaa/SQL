package JDBC.dao.forShoppingCart;

import JDBC.entity.ShoppingCart;
import workers.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDAOImpl implements ShoppingCartDAO {
    private JDBCConnection dbWorker;

    public ShoppingCartDAOImpl() {
        this.dbWorker = new JDBCConnection();
    }

    public List<ShoppingCart> getAll() throws SQLException {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.shopping_cart");
        while(resultSet.next()) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUserId(resultSet.getInt("user_id"));
            shoppingCart.setProductId(resultSet.getInt("product_id"));
            shoppingCarts.add(shoppingCart);
        }
        System.out.println("getAll shoppingCart");
        return shoppingCarts;
    }

    public List<ShoppingCart> getAllByUserId(int id) throws SQLException {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.shopping_cart WHERE user_id =" + id);
        while(resultSet.next()) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUserId(resultSet.getInt("user_id"));
            shoppingCart.setProductId(resultSet.getInt("product_id"));
            shoppingCarts.add(shoppingCart);
        }
        System.out.println("getAllByUserId shoppingCart");
        return shoppingCarts;
    }

    public List<ShoppingCart> getAllByProductId(int id) throws SQLException {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.shopping_cart WHERE product_id =" + id);
        while(resultSet.next()) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUserId(resultSet.getInt("user_id"));
            shoppingCart.setProductId(resultSet.getInt("product_id"));
            shoppingCarts.add(shoppingCart);
        }
        System.out.println("getAllByProductId shoppingCart");
        return shoppingCarts;
    }

    public ShoppingCart getByUserIdAndProductId(int userId, int productId) throws SQLException {
        ShoppingCart shoppingCart = new ShoppingCart();
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.shopping_cart WHERE product_id = " + productId + " AND user_id = " + userId);
        resultSet.next();
        shoppingCart.setUserId(resultSet.getInt("user_id"));
        shoppingCart.setProductId(resultSet.getInt("product_id"));
        System.out.println("getByUserIdAndProductId shoppingCart");
        return shoppingCart;
    }

    public void deleteProductFromUserShoppingCart (int userId, int productId) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("DELETE FROM shop.shopping_cart WHERE user_id = " + userId + " and product_id = " + productId);
        System.out.println("Deleted shoppingCart: " + resultSet);
    }


    public void save(ShoppingCart shoppingCart) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("INSERT shop.shopping_cart(user_id, product_id) VALUES (" +
                shoppingCart.getUserId() + ", " +
                shoppingCart.getProductId() + ")");
        System.out.println("Added shoppingCart: " + resultSet);
    }

}
