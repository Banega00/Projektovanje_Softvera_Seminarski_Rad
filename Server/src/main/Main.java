/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.sql.SQLException;
import persistence.db.DBConnectionFactory;
import view.FormMain;

/**
 *
 * @author Bane
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        new FormMain().setVisible(true);
    }
    
}
