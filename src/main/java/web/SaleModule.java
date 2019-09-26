/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.SaleDAO;
import org.jooby.Jooby;
import org.jooby.Status;
import shopping.Sale;

/**
 *
 * @author hansp965
 */
public class SaleModule extends Jooby {

    private final SaleDAO saleDao;

    public SaleModule(SaleDAO saleDao1) {
        this.saleDao = saleDao1;

        post("/api/sales", (req, rsp) -> {
            Sale sale = req.body().to(Sale.class);
            System.out.print(sale.toString());
            saleDao.save(sale);
            rsp.status(Status.CREATED);
        });
    }
}
