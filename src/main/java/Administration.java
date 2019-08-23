
import dao.DbConnection;
import gui.MainMenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hansp965
 */
public class Administration{
    private static DbConnection dao = new DbConnection();
    private static MainMenu menu = new MainMenu(dao); 
          
    public static void main(String[] args){
            menu.setVisible(true);
    }
}
