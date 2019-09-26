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
import shopping.Product;

/**
 *
 * @author hansp965
 */
public class DAO implements DAOInterface {

    private final DbConnection DbConnection;
    private String DEFAULT_URI;

    public DAO(DbConnection DbConnection, String URI) {
        this.DbConnection = DbConnection;
        DEFAULT_URI = URI;
    }

    @Override
    public void addProduct(Product product) {
        String sql = "insert into Product (ProductID, Name, Description, Category, ListPrice, QuantityInStock) values (?,?,?,?,?,?)";

        try (
                Connection dbCon = DbConnection.getConnection(DEFAULT_URI);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, product.getProductID());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getCategory());
            stmt.setBigDecimal(5, product.getListPrice());
            stmt.setBigDecimal(6, product.getQuantityInStock());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteProduct(Product product) {
        String sql = "delete from Product where ProductID = ?";

        try (
                Connection dbCon = DbConnection.getConnection(DEFAULT_URI);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, product.getProductID());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<String> getCategoryList() {
        String sql = "select DISTINCT Category from Product";

        try (
                Connection dbCon = DbConnection.getConnection(DEFAULT_URI);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();
            Collection<String> categories = new ArrayList<>();

            while (rs.next()) {
                String category = rs.getString("Category");
                categories.add(category);
            }

            return categories;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<Product> getProductCategory(String c) {

        String sql = "select * from Product where Category = ?";

        try (
                Connection connection = DbConnection.getConnection(DEFAULT_URI);
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, c);
            ResultSet rs = stmt.executeQuery();
            Collection<Product> products = new ArrayList<>();

            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                String category = rs.getString("Category");
                BigDecimal price = rs.getBigDecimal("ListPrice");
                BigDecimal quantity = rs.getBigDecimal("QuantityinStock");

                products.add(new Product(productID, name, description, category, price, quantity));
            }

            return products;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    public Product getProductFromID(String id) {
        String sql = "select * from Product where ProductID = ?";

        try (
                Connection connection = DbConnection.getConnection(DEFAULT_URI);
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String productID = rs.getString("ProductID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                String category = rs.getString("Category");
                BigDecimal price = rs.getBigDecimal("ListPrice");
                BigDecimal quantity = rs.getBigDecimal("QuantityinStock");

                return new Product(productID, name, description, category, price, quantity);

            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<Product> getProductList() {
        String sql = "select * from Product order by ProductID";

        try (
                Connection dbCon = DbConnection.getConnection(DEFAULT_URI);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
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
            throw new DAOException(ex.getMessage(), ex);
        }
    }
}
