/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.DbConnection;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.json.Gzon;
import web.auth.BasicHttpAuthenticator;

/**
 *
 * @author hansp965
 */
public class Server extends Jooby {

    private static DbConnection dao = new DbConnection();

    public Server() {
        port(8080);
        use(new Gzon());
        use(new AssetModule());
        
        List<String> noAuth = Arrays.asList("/api/register");
        use(new BasicHttpAuthenticator(dao.getCustomerDAO(), noAuth));
        
        use(new ProductModule(dao.getDAO()));
        use(new CustomerModule(dao.getCustomerDAO()));
        use(new SaleModule(dao.getSaleDAO()));    
    }

    public static void main(String[] args) throws Exception {
        System.out.println("\nStarting Server.");
        Server server = new Server();
        CompletableFuture.runAsync(() -> {
            server.start();
        });
        server.onStarted(() -> {
            System.out.println("\nPress Enter to stop the server.");
        });
// wait for user to hit the Enter key
        System.in.read();
        System.exit(0);
    }
}
