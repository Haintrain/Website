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
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import shopping.Customer;
import shopping.Product;
import shopping.Sale;
import shopping.SaleItem;

/**
 *
 * @author hansp965
 */
public class SaleDAO implements SaleDAOInterface {

    private final DbConnection DbConnection;
    private String DEFAULT_URI;

    public SaleDAO(DbConnection DbConnection, String URI) {
        this.DbConnection = DbConnection;
        DEFAULT_URI = URI;
    }

    @Override
    public void save(Sale sale) {

        String insertOrder = "insert into Sale (SaleDate, Status, CustomerID) values (?,?,?)";
        String insertOrderItem = "insert into SaleItem (QuantityPurchased, SalePrice, ProductID, SaleID) values (?,?,?,?)";
        String updateProduct = "update Product set QuantityInStock = ? where ProductID = ?";
        Connection con = DbConnection.getConnection(DEFAULT_URI);

        try {
            try (
                    PreparedStatement insertOrderStmt = con.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement insertOrderItemStmt = con.prepareStatement(insertOrderItem);
                    PreparedStatement updateProductStmt = con.prepareStatement(updateProduct);) {

                // since saving and order involves multiple statements across
                // multiple tables we need to control the transaction ourselves
                // to ensure our DB remains consistent
                // turn off auto-commit which effectively starts a new transaction
                con.setAutoCommit(false);

                Customer customer = sale.getCustomer();

                // #### save the order ### //
                // add a date to the sale if one doesn't already exist
                if (sale.getDate() == null) {
                    sale.setDate(LocalDate.now());
                }

                // convert sale date into to java.sql.Timestamp
                LocalDate date = sale.getDate();
                Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());

                // ****
                // write code here that saves the timestamp and username in the
                // sale table using the insertOrderStmt statement.
                // ****
                insertOrderStmt.setTimestamp(1, timestamp);
                insertOrderStmt.setString(2, "Order processing");
                insertOrderStmt.setString(3, customer.getUsername());
                insertOrderStmt.executeUpdate();
                
                // get the auto-generated order ID from the database
                ResultSet rs = insertOrderStmt.getGeneratedKeys();

                Integer orderId = null;

                if (rs.next()) {
                    orderId = rs.getInt(1);
                } else {
                    throw new DAOException("Problem getting generated Order ID");
                }              

                // ## save the order items ## //
                Collection<SaleItem> items = sale.getSaleItems();

                // ****
                // write code here that iterates through the order items and
                // saves them using the insertOrderItemStmt statement.
                // ****
                
                for(SaleItem item : items){
                    insertOrderItemStmt.setBigDecimal(1, item.getQuantityPurchased());
                    insertOrderItemStmt.setBigDecimal(2, item.getProduct().getListPrice());
                    insertOrderItemStmt.setString(3, item.getProduct().getProductID());
                    insertOrderItemStmt.setInt(4, orderId);
                    insertOrderItemStmt.executeUpdate();
                }
                
                
                // ## update the product quantities ## //
                for (SaleItem item : items) {

                    Product product = item.getProduct();
                    product.setQuantityInStock(product.getQuantityInStock().subtract(item.getQuantityPurchased()));
                    
                    updateProductStmt.setBigDecimal(1, product.getQuantityInStock());
                    updateProductStmt.setString(2, product.getProductID());
                    updateProductStmt.executeUpdate();
                }

                // commit the transaction
                con.setAutoCommit(true);
            }
        } catch (SQLException ex) {

            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);

            try {
                // something went wrong so rollback
                con.rollback();

                // turn auto-commit back on
                con.setAutoCommit(true);

                // and throw an exception to tell the user something bad happened
                throw new DAOException(ex.getMessage(), ex);
            } catch (SQLException ex1) {
                throw new DAOException(ex1.getMessage(), ex1);
            }

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
