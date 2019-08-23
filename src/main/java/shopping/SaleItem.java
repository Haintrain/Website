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
public class SaleItem {
    private BigDecimal quantityPurchased;
    private BigDecimal salePrice;
    private Product product;
    
    private BigDecimal getItemTotal(){
        return null;
    }

    public BigDecimal getQuantityPurchased() {
        return quantityPurchased;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantityPurchased(BigDecimal quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
}
