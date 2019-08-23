/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.h2.jdbcx.JdbcConnectionPool;
import shopping.Product;

/**
 *
 * @author hansp965
 */
public class DbConnection implements DAOInterface {

    public DbConnection() {
    }

    /*public DbConnection() {
    }*/
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "sa";

    private static final Integer DB_PORT = 9092;
    private static final String DEFAULT_URI = "jdbc:h2:tcp://localhost:" + DB_PORT + "/project";

    private static JdbcConnectionPool pool;

    public static Connection getConnection(String uri) {

        if (pool == null) {
            pool = JdbcConnectionPool.create(uri, USERNAME, PASSWORD);
        }

        try {
            return pool.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getDefaultConnectionUri() {
        return DEFAULT_URI;
    }

    @Override
    public void addProduct(Product product) {
        String sql="insert into Product (ProductID, Name, Description, Category, ListPrice, QuantityInStock) values (?,?,?,?,?,?)";

        try (
            Connection dbCon = DbConnection.getConnection(DEFAULT_URI);
            PreparedStatement stmt = dbCon.prepareStatement(sql);
        ) {
            stmt.setString(1, product.getProductID());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getCategory());
            stmt.setBigDecimal(5, product.getListPrice());
            stmt.setBigDecimal(6, product.getQuantityInStock());

            stmt.executeUpdate();

        } catch (SQLException ex) {  
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<String> getCategoryList() {
        String sql = "select DISTINCT Category from Product";

    try (
        Connection dbCon = DbConnection.getConnection(DEFAULT_URI);
        PreparedStatement stmt = dbCon.prepareStatement(sql);
    ) {
        ResultSet rs = stmt.executeQuery();
        Collection<String> categories = new ArrayList<>();

        while (rs.next()) {
            String category = rs.getString("Category");
            categories.add(category);
        }

        return categories;

    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }    }

    @Override
    public Collection<Product> getProductCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getProductFromID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Product> getProductList() {
        String sql = "select * from Product order by ProductID";

    try (
        Connection dbCon = DbConnection.getConnection(DEFAULT_URI);
        PreparedStatement stmt = dbCon.prepareStatement(sql);
    ) {
        ResultSet rs = stmt.executeQuery();
        Collection<Product> products = new ArrayList<>();

        while (rs.next()) {

            String id = rs.getString("ProductID");
            String name = rs.getString("Name");
            String description = rs.getString("Description");
            String category = rs.getString("Category");
            BigDecimal price = rs.getBigDecimal("ListPrice");
            BigDecimal quantity = rs.getBigDecimal("QuantityInStock");

            Product p = new Product(id, name, description, category, price, quantity);

            products.add(p);
        }

        return products;

    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }    }
}
