/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
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
        fail("Not implemented yet");
    }

    @Test
    public void testAddProduct() {
        fail("Not implemented yet");
    }

    @Test
    public void testGetProductList() {
        fail("Not implemented yet");
    }

    @Test
    public void testRemoveProduct() {
        fail("Not implemented yet");
    }
    
}
