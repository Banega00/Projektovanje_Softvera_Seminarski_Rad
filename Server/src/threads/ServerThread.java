/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bane
 */
public class ServerThread extends Thread{
    
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;
    public ServerThread() throws IOException {
        this.clients = new ArrayList<>();
        
        try {
            //TODO ovde ubaci da se port cita iz property fajla
            this.serverSocket = new ServerSocket(1312);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    @Override
    public void run() {
        while(!this.serverSocket.isClosed()){
            try {
                Socket socket = this.serverSocket.accept();
                ClientHandler ch = new ClientHandler(socket,this);
                System.out.println("Novi klijent se povezao");
                ch.start();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
