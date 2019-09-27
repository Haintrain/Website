/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerDAO;
import org.jooby.Jooby;
import org.jooby.Result;
import org.jooby.Status;
import shopping.Customer;

/**
 *
 * @author hansp965
 */
public class CustomerModule extends Jooby {

    private final CustomerDAO custDao;

    public CustomerModule(CustomerDAO custDao1) {
        this.custDao = custDao1;

        get("/api/customers/:username", (req) -> {
            String id = req.param("username").value();
            if (custDao.getCustomerFromUsername(id) != null) {
                return custDao.getCustomerFromUsername(id);
            } else {
                return new Result().status(Status.NOT_FOUND);
            }
        });
        post("/api/register", (req, rsp) -> {
            Customer customer = req.body().to(Customer.class);
            custDao.save(customer);
            rsp.status(Status.CREATED);
        });
    }
}
