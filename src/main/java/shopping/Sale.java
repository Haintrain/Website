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
    private Integer saleID;
    private LocalDate date;
    private String status;
    private Customer customer;
    private ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();
    
    public BigDecimal getTotal(){
        return null;
    }
    
    public void addItem(SaleItem saleItem){
        
    }
}
