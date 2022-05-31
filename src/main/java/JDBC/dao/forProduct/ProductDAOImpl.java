package JDBC.dao.forProduct;

import JDBC.entity.Product;
import workers.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO{
    private JDBCConnection dbWorker;

    public ProductDAOImpl() {
        this.dbWorker = new JDBCConnection();
    }

    public List<Product> getAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.product");
        while(resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
            products.add(product);
        }
        System.out.println("getAll product");
        return products;
    }

    public Product getById(int id) throws SQLException{
        Product product = new Product();
        Statement statement = dbWorker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.product WHERE id = " + id);
        resultSet.next();

        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getDouble("price"));
        System.out.println("GetById product");
        return product;
    }

    public void save(Product product) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("INSERT shop.product(name, price) VALUES ('" +
                product.getName() + "', " +
                product.getPrice() + ")");
        System.out.println("Added product: " + resultSet);
    }

    public void update(Product product) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("UPDATE shop.product SET " +
                "name = '" + product.getName() +
                "', price = " + product.getPrice() +
                " WHERE id = " + product.getId());
        System.out.println("Updated product: " + resultSet);
    }

    public void delete(int id) throws SQLException {
        Statement statement = dbWorker.getConnection().createStatement();
        int resultSet = statement.executeUpdate("DELETE FROM shop.product WHERE Id = " + id);
        System.out.println("Deleted product: " + resultSet);
    }
}
