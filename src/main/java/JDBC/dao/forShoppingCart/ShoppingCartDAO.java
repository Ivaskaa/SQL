package JDBC.dao.forShoppingCart;

import JDBC.entity.ShoppingCart;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingCartDAO {
    public List<ShoppingCart> getAll() throws SQLException;
    public List<ShoppingCart> getAllByUserId(int id) throws SQLException;
    public List<ShoppingCart> getAllByProductId(int id) throws SQLException;
    public ShoppingCart getByUserIdAndProductId(int userId, int productId) throws SQLException;
    public void deleteProductFromUserShoppingCart (int userId, int productId) throws SQLException;
    public void save(ShoppingCart shoppingCart) throws SQLException;
}
