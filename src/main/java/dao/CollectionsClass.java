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
    public static Collection<String> categoryList = new ArrayList<String>();  
    
    public void addProduct(Product product){
        productList.add(product);
    }

    public Collection<Product> getProductList() {
        return productList;
    }
    
    public void deleteProduct(Product product){
        productList.remove(product);
    }
    
    private void getCategories(){
        for(Product product: productList){
            String category = product.getCategory();
            if(!categoryList.contains(category)){
                categoryList.add(category);
            }
        }
    }
    
    public Collection<String> getCategoryList(){
        getCategories();
        return categoryList;
    }
}
