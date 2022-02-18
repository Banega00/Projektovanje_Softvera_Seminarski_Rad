/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bane
 */
public class DBConnectionFactory {

    private Connection conn;
    private static DBConnectionFactory instance;

    private DBConnectionFactory() {
    }

    public static DBConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DBConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException, IOException {
        if (conn == null || conn.isClosed()) {
            try {
                //TODO read environment variables
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ps_database?serverTimezone=Europe/Prague", "root", "root");
                System.out.println("Successful connection!");
            } catch (SQLException ex) {
                System.out.println("Error while establishing connection.");
                throw ex;
            }
        }
        return conn;
    }
}
