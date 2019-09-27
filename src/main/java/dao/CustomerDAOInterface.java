/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import shopping.Customer;
import web.auth.CredentialsValidator;

/**
 *
 * @author hansp965
 */
public abstract interface CustomerDAOInterface extends CredentialsValidator{

    void save(Customer customer);

    Customer getCustomerFromUsername(String username);
}
