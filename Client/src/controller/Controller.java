/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Operation;
import common.Request;
import common.Response;
import common.ResponseType;
import communication.Communication;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Bane
 */
public class Controller {

    private static Controller instance;
    private Account userAccount;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void login(Account account) throws Exception {
        Request request = new Request(Operation.LOGIN, account);
        try {
            Response response = Communication.getInstance().login(request);
            
            if(response.getResponseType() == ResponseType.SUCCESS){
                this.userAccount = (Account) response.getData();
            }else{
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, response.getError());
                throw response.getError();
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    
}
