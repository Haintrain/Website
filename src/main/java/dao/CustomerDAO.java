package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import shopping.Customer;
import java.util.HashMap;
import java.util.Map;

public final class CustomerDAO implements CustomerDAOInterface {

    // customers stored by username
    private static final Map<String, Customer> customers = new HashMap<>();
    private final DbConnection DbConnection;
    private String DEFAULT_URI;

    public CustomerDAO(DbConnection DbConnection, String URI) {
        this.DbConnection = DbConnection;
        DEFAULT_URI = URI;
    }

    /*@Override
    public void save(Customer customer) {
        String sql = "insert into Customer (CustomerID, Username, FirstName, LastName, Password, EmailAddress, ShippingAddress) values (?,?,?,?,?,?,?)";

        try (
                Connection dbCon = DbConnection.getConnection(DEFAULT_URI);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setInt(1, customer.getCustomerID());
            stmt.setString(2, customer.getUsername());
            stmt.setString(3, customer.getFirstName());
            stmt.setString(4, customer.getLastName());
            stmt.setString(5, customer.getPassword());
            stmt.setString(6, customer.getEmailAddress());
            stmt.setString(7, customer.getShippingAddress());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }*/

    @Override
    public void save(Customer customer) {
        System.out.println("Saving customer: " + customer);
        customers.put(customer.getUsername(), customer);
    }

    @Override
    public Customer getCustomer(String username) {
        return customers.get(username);
    }

    @Override
    public Boolean validateCredentials(String username, String password) {
        if (customers.containsKey(username)) {
            return customers.get(username).getPassword().equals(password);
        } else {
            return false;
        }
    }

}
