/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bane
 */
public class ClientHandler extends Thread {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ServerThread serverThread;

    ClientHandler(Socket socket, ServerThread serverThread) {

        try {
            this.serverThread = serverThread;
            this.socket = socket;
            this.out = new ObjectOutputStream(this.socket.getOutputStream());
            this.in = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (!this.socket.isClosed()) {
        }
    }

}
