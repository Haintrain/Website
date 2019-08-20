package shopping;


import java.math.BigDecimal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hansp965
 */
public class Product {
    private String productID;
    private String name;
    private String description;
    private String category;
    private BigDecimal listPrice;
    private BigDecimal quantityInStock;

    public Product(String string, String name1, String desc, String cat, BigDecimal bigDecimal, BigDecimal bigDecimal0) {
        setProductID(string);
        setName(name1);
        setCategory(cat);
        setDescription(desc);
        setListPrice(bigDecimal);
        setQuantityInStock(bigDecimal0);
    }

    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public BigDecimal getQuantityInStock() {
        return quantityInStock;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public void setQuantityInStock(BigDecimal quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return productID + ", " + name + ", " + category;
    }
}
