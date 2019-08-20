/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import shopping.Product;

/**
 *
 * @author hansp965
 */
public class CollectionsClass {
    public static Collection<Product> productList = new HashSet<Product>();
    public static Collection<String> categoryList = new HashSet<String>();  
    public static Map<String, Product> IDList = new HashMap<>();  
    public static Multimap<String, Product> productCategoryList = HashMultimap.create();
    
    public void addProduct(Product product){
        productList.add(product);
        IDList.put(product.getProductID(), product);
        productCategoryList.put(product.getCategory(), product);
    }

    public Collection<Product> getProductList() {
        return productList;
    }
    
    public Collection<Product> getProductCategory(String category) {
        return productCategoryList.get(category);
    }
    
    public void deleteProduct(Product product){
        productList.remove(product);
    }
    
    public Product getProductFromID(String id){
        if(!IDList.containsKey(id)){
            return null;
        }
        Product product = IDList.get(id);
        return product;
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
