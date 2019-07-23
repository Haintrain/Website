/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Collection;
import shopping.Product;

/**
 *
 * @author hansp965
 */
public class CollectionsClass {
    public static Collection<Product> productList = new ArrayList<Product>();

    public void addProduct(Product product){
        productList.add(product);
    }

    public Collection<Product> getProductList() {
        return productList;
    }
    
    public void removeProduct(Product product){
        productList.remove(product);
    }
}
