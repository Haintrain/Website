/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import shopping.Sale;

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
        String sql = "insert into Sale (SaleID, Date, Status, Customer, SaleItem) values (?,?,?,?,?)";
        Date date = Date.valueOf(sale.getDate());
        
        try (
                Connection dbCon = DbConnection.getConnection(DEFAULT_URI);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, sale.getSaleID());
            stmt.setDate(2, date);
            stmt.setString(3, sale.getStatus());
            stmt.setString(4, sale.getCustomer().getCustomerID());
            stmt.setString(5, "Place Holder");
            
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
}
