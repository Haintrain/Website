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
public class DbConnection {

    private static final String USERNAME = "sa";
    private static final String PASSWORD = "sa";

    private static final Integer DB_PORT = 9092;
    private static final String DEFAULT_URI = "jdbc:h2:tcp://localhost:" + DB_PORT + "/project";
    
    CustomerDAO custDao = new CustomerDAO(this, DEFAULT_URI);
    DAO dao = new DAO(this, DEFAULT_URI);

    public DbConnection() {  
    }

    public DAO getDAO(){
        return dao;
    }
    
    public CustomerDAO getCustomerDAO(){
        return custDao;
    }
    private static JdbcConnectionPool pool;

    public static Connection getConnection(String uri) {

        if (pool == null) {
            pool = JdbcConnectionPool.create(uri, USERNAME, PASSWORD);
        }

        try {
            return pool.getConnection();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    public static String getDefaultConnectionUri() {
        return DEFAULT_URI;
    }
}
