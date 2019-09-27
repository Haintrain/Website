package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import shopping.Customer;

public class CustomerDAO implements CustomerDAOInterface{

    private final DbConnection DbConnection;
    private String DEFAULT_URI;

    public CustomerDAO(DbConnection DbConnection, String URI) {
        this.DbConnection = DbConnection;
        DEFAULT_URI = URI;
    }

    @Override
    public void save(Customer customer) {
        String sql = "insert into Customer (Username, FirstName, LastName, Password, EmailAddress, ShippingAddress) values (?,?,?,?,?,?)";

        try (
                Connection dbCon = DbConnection.getConnection(DEFAULT_URI);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getPassword());
            stmt.setString(5, customer.getEmailAddress());
            stmt.setString(6, customer.getShippingAddress());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Customer getCustomerFromUsername(String id) {
        String sql = "select * from Customer where Username = ?";

        try (
                Connection connection = DbConnection.getConnection(DEFAULT_URI);
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String customerID = rs.getString("CustomerID");
                String username = rs.getString("Username");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String password = rs.getString("Password");
                String emailAddress = rs.getString("EmailAddress");
                String shippingAddress = rs.getString("ShippingAddress");

                return new Customer(customerID, username, firstName, lastName, password, emailAddress, shippingAddress);

            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public Boolean validateCredentials(String username, String password) {
        String sql = "select * from Customer where Username = ?";
//        System.out.print(username + " " + password);
 
        try (
                Connection connection = DbConnection.getConnection(DEFAULT_URI);
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
 
            if (rs.next()) {
                String pass = rs.getString("Password");
                if(password.equals(pass)){
                    return true;
                } 
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
}
