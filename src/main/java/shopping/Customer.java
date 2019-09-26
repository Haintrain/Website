package shopping;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hansp965
 */
public class Customer {
    @NotNull(message = "ID must be provided.")
    @NotBlank(message = "ID must be provided.")
    private String customerID;
    
    @NotNull(message = "Username must be provided.")
    @NotBlank(message = "Username must be provided.")
    @Length(min=2, message="Username must contain at least two characters.")
    private String username;
      
    private String firstName;
    private String lastName;
    
    @NotNull(message = "Password must be provided.")
    @NotBlank(message = "Password must be provided.")
    @Length(min=7, message="Password must contain at least seven characters.")
    private String password;
    private String emailAddress;
    private String shippingAddress;

    public Customer(String customerID, String username, String firstName, String lastName, String password, String emailAddress, String shippingAddress) {
        setCustomerID(customerID);
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setEmailAddress(emailAddress);
        setShippingAddress(shippingAddress);
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", username=" + username + '}';
    }
}
