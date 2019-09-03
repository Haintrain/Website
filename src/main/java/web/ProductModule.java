/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.DAO;
import org.jooby.Jooby;

/**
 *
 * @author hansp965
 */
public class ProductModule extends Jooby {

    private final DAO dao;
    
    public ProductModule(DAO dao) {
        this.dao = dao;
        
        get("/api/products", () -> dao.getProductList());
        get("/api/products/:id", (req) -> {
            String id = req.param("id").value();
            return dao.getProductFromID(id);
        });
    }
}
