/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import shopping.Product;

/**
 *
 * @author hansp965
 */
public class CollectionsClassTest {
    
    public CollectionsClassTest() {
   
    }
    
    private CollectionsClass dao = new CollectionsClass();
    private Product prodOne;
    private Product prodTwo;
    private Product prodThree;

    
    @Before
    public void setUp() {
        prodOne = new Product("1", "name1", "cat1", "desc1",new BigDecimal("11.00"), new BigDecimal("22.00"));
        prodTwo = new Product("2", "name2", "cat2", "desc2",new BigDecimal("33.00"), new BigDecimal("44.00"));
        prodThree = new Product("3", "name3", "cat3", "desc3",new BigDecimal("55.00"), new BigDecimal("66.00"));
        // save the products
        dao.addProduct(prodOne);
        dao.addProduct(prodTwo);
    }
    
    @After
    public void tearDown() {
        dao.deleteProduct(prodOne);
        dao.deleteProduct(prodTwo);
        dao.deleteProduct(prodThree);
    }

    @Test
    public void testAddProduct() {
        dao.addProduct(prodThree);
        assertTrue("Ensure that the product was saved", dao.getProductList().contains(prodThree));
    }

    @Test
    public void testGetProductList() {
        Collection<Product> products = dao.getProductList();

        assertTrue("prodOne should exist", products.contains(prodOne));
        assertTrue("prodTwo should exist", products.contains(prodTwo));

        assertEquals("Only 2 products in result", 2, products.size());
    }

    @Test
    public void testRemoveProduct() {
        assertTrue("Ensure that the product does exist", dao.getProductList().contains(prodOne));
        dao.deleteProduct(prodOne);
        assertFalse("Ensure that the product does not exist", dao.getProductList().contains(prodOne));
    }
    
    @Test
    public void testGetCategoryList() {
        assertTrue("Category List", dao.getCategoryList().contains(prodOne.getCategory()));
    }
    
    @Test
    public void testGetProductFromID(){
        assertTrue("Getting product from ID", dao.getProductFromID("1").getName().equals("name1"));
    }
    
    @Test
    public void testGetProductCategory(){
        assertTrue("Getting product category list", dao.getProductCategory(prodOne.getCategory()).contains(prodOne));
    }
}
