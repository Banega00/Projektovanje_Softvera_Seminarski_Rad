/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import common.Request;
import common.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Bane
 */
public class Communication {
    private static Communication instance;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    private Communication(){
        
    }
    
    public static Communication getInstance(){
        if (instance == null) instance = new Communication();
        return instance;
    }

    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.setSocketStreams();
    }

    public Socket getSocket() {
        return socket;
    }

    private void setSocketStreams() throws IOException {
        this.out = new ObjectOutputStream(this.socket.getOutputStream());
        this.in = new ObjectInputStream(this.socket.getInputStream());
    }
    
    public Response login(Request request)throws Exception{
        this.out.writeObject(request);
        Response response = (Response) this.in.readObject();
        return response;
    }

//    public Response getAllManufacturers(Request request) throws Exception {
//        new Sender(socket).send(request);
//        System.out.println("Zahtev getAllManufacturers je poslat...");
//        return (Response) new Receiver(socket).receive();
//    }
//
//    public Response addProduct(Request request) throws Exception {
//        new Sender(socket).send(request);
//        System.out.println("Zahtev addProduct je poslat...");
//        return (Response) new Receiver(socket).receive();
//    }
}