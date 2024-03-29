/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import shopping.Product;

/**
 *
 * @author hansp965
 */
public abstract interface DAOInterface {

    void addProduct(Product product);

    void deleteProduct(Product product);

    Collection<String> getCategoryList();

    Collection<Product> getProductCategory(String category);

    Product getProductFromID(String id);

    Collection<Product> getProductList();
    
}
