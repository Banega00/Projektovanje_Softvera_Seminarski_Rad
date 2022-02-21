/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import communication.Operation;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Employee;
import model.Meal;
import model.MealOffer;
import model.Order;

/**
 *
 * @author Bane
 */
public class ClientHandler extends Thread {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ServerThread serverThread;
    private Controller controller;
    private Account userAccount;
    private Date loginTime;
    ClientHandler(Socket socket, ServerThread serverThread, Controller controller) {

        try {
            this.socket = socket;
            this.serverThread = serverThread;
            this.controller = controller;
            this.out = new ObjectOutputStream(this.socket.getOutputStream());
            this.in = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (!this.socket.isClosed()) {
            try {
                Request request = (Request) this.in.readObject();
                Response response = handleRequest(request);
                if (response == null) {
                    continue;
                }

                this.out.writeObject(response);
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    this.socket.close();
                    this.serverThread.getClients().remove(this);
                } catch (IOException ex1) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.serverThread.getClients().remove(this);
    }

    private Response handleRequest(Request request) {
        switch (request.getOperation()) {
            case LOGIN:
                return this.login(request);
            case GET_EMPLOYEES:
                return this.getEmployees();
            case CREATE_ACCOUNT:
                return this.createAccount((Account) request.getData());
            case DEACTIVATE_ACCOUNT:
                return this.deactivateAccount((Account) request.getData());
            case GET_MEALS:
                return this.getMeals();
            case GET_MEAL_OFFERS:
                return this.getMealOffers();
            case GET_MEAL_OFFER:
                return this.getMealOffer((long) request.getData());
            case SAVE_MEAL_OFFER:
                return this.saveMealOffer((MealOffer) request.getData());
            case EDIT_MEAL_OFFER:
                return this.editMealOffer((MealOffer) request.getData());
            case SAVE_ORDER:
                return this.saveOrder((Order) request.getData());
            case GET_EMPLOYEE_ORDER:
                return this.getEmployeeOrder((Order) request.getData());
            case GET_ALL_ORDERS_FOR_MEAL_OFFER:
                return this.getAllOrders((MealOffer) request.getData());
            case CREATE_GROUP_ORDER:
                return this.createGroupOrder((MealOffer) request.getData());
            default:
                return new Response(ResponseType.ERROR, null, new UnsupportedOperationException());
        }
    }

    private Response login(Request request) {
        Response response;
        try {

            Account requestEmployee = (Account) request.getData();
            Account employeeAccount = this.controller.login(requestEmployee.getUsername(), requestEmployee.getPassword());
            for (ClientHandler ch : this.serverThread.getClients()) {
                if (ch!=this && ch.getUserAccount()!=null && ch.getUserAccount().getId() == employeeAccount.getId()) {
                    throw new Exception("Korisnik je već ulogovan");
                }
            }
            response = new Response(ResponseType.SUCCESS, employeeAccount, null);
            this.userAccount = employeeAccount;
            this.loginTime = new Date();
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }

    private Response getEmployees() {
        List<Employee> employees;
        Response response;
        try {
            employees = this.controller.getEmployees();
            response = new Response(ResponseType.SUCCESS, employees, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }

    private Response createAccount(Account account) {
        Response response;
        try {
            this.controller.createAcoount(account);
            response = new Response(ResponseType.SUCCESS, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }

    private Response deactivateAccount(Account account) {
        Response response;
        try {
            this.controller.deactivateAccount(account);
            response = new Response(ResponseType.SUCCESS, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }

    private Response getMeals() {
        Response response;
        try {
            List<Meal> mealList = this.controller.getMeals();
            response = new Response(ResponseType.SUCCESS, mealList, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }

    private Response getMealOffers() {
        Response response;
        try {
            List<MealOffer> mealOffers = this.controller.getMealOffers();
            response = new Response(ResponseType.SUCCESS, mealOffers, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }

    private Response getMealOffer(long mealId) {
        Response response;
        try {
            MealOffer mealOffer = this.controller.getMealOffer(mealId);
            response = new Response(ResponseType.SUCCESS, mealOffer, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }

    private Response saveMealOffer(MealOffer mealOffer) {
        Response response;
        try {
            this.controller.saveMealOffer(mealOffer);
            response = new Response(ResponseType.SUCCESS, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }

    private Response editMealOffer(MealOffer mealOffer) {
        Response response;
        try {
            this.controller.editMealOffer(mealOffer);
            response = new Response(ResponseType.SUCCESS, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }

    private Response saveOrder(Order order) {
        Response response;
        try {
            this.controller.saveOrder(order);
            response = new Response(ResponseType.SUCCESS, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }

    private Response getEmployeeOrder(Order order) {
        Response response;
        try {
            Order o = this.controller.getEmployeeOrder(order);
            response = new Response(ResponseType.SUCCESS, o, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }

    private Response getAllOrders(MealOffer mealOffer) {
        Response response;
        try {
            List<Order> orders = this.controller.getAllOrders(mealOffer);
            response = new Response(ResponseType.SUCCESS, orders, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }
    
    private Response createGroupOrder(MealOffer mealOffer) {
        Response response;
        try {
            String groupOrder = this.controller.createGroupOrder(mealOffer);
            response = new Response(ResponseType.SUCCESS, groupOrder, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    void sendDisconnectEvent() {
        try {
            this.out.writeObject(new Request(Operation.DISCONNECT, null));
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
