package shopping;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hansp965
 */
public class Sale {
    private String saleID;
    private LocalDate date;
    private String status;
    private Customer customer;
    private ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();
      
    public Sale(String saleID, LocalDate date, String status, Customer customer) {
        this.saleID = saleID;
        this.date = date;
        this.status = status;
        this.customer = customer;
    }

    
    public BigDecimal getTotal(){
        return null;
    }
    
    public void addItem(SaleItem saleItem){
        
    }

    public String getSaleID() {
        return saleID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setSaleItems(ArrayList<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }
    
    
}
