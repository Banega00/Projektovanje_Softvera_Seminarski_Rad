/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.Operation;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import communication.Communication;
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
            Response response = Communication.getInstance().send(request);
            
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

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public List<Employee> getEmployees() throws Exception {
        Request request = new Request(Operation.GET_EMPLOYEES, null);
        try {
            Response response = Communication.getInstance().send(request);
            if(response.getResponseType()==ResponseType.ERROR) throw response.getError();
            
            return (List<Employee>) response.getData();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public void createAccount(Account account) throws Exception {
        Request request = new Request(Operation.CREATE_ACCOUNT, account);
        try {
            Response response = Communication.getInstance().send(request);
            if(response.getResponseType()==ResponseType.ERROR) throw response.getError();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public void deactiveAccount(Account account) throws Exception {
        Request request = new Request(Operation.DEACTIVATE_ACCOUNT, account);
        try {
            Response response = Communication.getInstance().send(request);
            if(response.getResponseType() == ResponseType.ERROR) throw response.getError();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public List<Meal> getMeals() throws Exception {
        Request request = new Request(Operation.GET_MEALS, null);
        try {
            Response response = Communication.getInstance().send(request);
            if(response.getResponseType() == ResponseType.ERROR) throw response.getError();
            
            return (List<Meal>) response.getData();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public List<MealOffer> getMealOffers() throws Exception {
        Request request = new Request(Operation.GET_MEAL_OFFERS, null);
        try {
            Response response = Communication.getInstance().send(request);
            if(response.getResponseType() == ResponseType.ERROR) throw response.getError();
            
            return (List<MealOffer>) response.getData();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public MealOffer getMealOffer(long id) throws Exception {
            Request request = new Request(Operation.GET_MEAL_OFFER, id);
        try {
            Response response = Communication.getInstance().send(request);
            if(response.getResponseType() == ResponseType.ERROR) throw response.getError();
            
            return (MealOffer) response.getData();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public void saveMealOffer(MealOffer mo) throws Exception {
        mo.setAccount(userAccount);
        Request request = new Request(Operation.SAVE_MEAL_OFFER, mo);
        try {
            Response response = Communication.getInstance().send(request);
            if(response.getResponseType() == ResponseType.ERROR) throw response.getError();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public void editMealOffer(MealOffer mo) throws Exception {
        mo.setAccount(userAccount);
        Request request = new Request(Operation.EDIT_MEAL_OFFER, mo);
        try {
            Response response = Communication.getInstance().send(request);
            if(response.getResponseType() == ResponseType.ERROR) throw response.getError();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public void saveOrder(Order order) throws Exception {
        Request request = new Request(Operation.SAVE_ORDER, order);
        try {
            Response response = Communication.getInstance().send(request);
            if(response.getResponseType() == ResponseType.ERROR) throw response.getError();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public Order getOrderedMealsForEmployee(MealOffer mealOffer) throws Exception {
        Request request = new Request(Operation.GET_EMPLOYEE_ORDER, new Order(mealOffer, this.userAccount, null));
        try {
            Response response = Communication.getInstance().send(request);
            if(response.getResponseType() == ResponseType.ERROR) throw response.getError();
            return (Order) response.getData();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public List<Order> getAllOrders(MealOffer mealOffer) throws Exception{
        Request request = new Request(Operation.GET_ALL_ORDERS_FOR_MEAL_OFFER, mealOffer);
        try {
            Response response = Communication.getInstance().send(request);
            if(response.getResponseType() == ResponseType.ERROR) throw response.getError();
            return (List<Order>) response.getData();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public String createGroupOrder(MealOffer mealOffer) throws Exception {
        Request request = new Request(Operation.CREATE_GROUP_ORDER, mealOffer);
        try {
            Response response = Communication.getInstance().send(request);
            if(response.getResponseType() == ResponseType.ERROR) throw response.getError();
            return (String) response.getData();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}
