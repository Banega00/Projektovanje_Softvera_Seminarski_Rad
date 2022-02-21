/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.Account;
import model.Employee;
import model.Meal;
import model.MealOffer;
import model.Order;
import so.AbstractSO;
import so.CreateAccountSO;
import so.CreateGroupOrderSO;
import so.DeactivateAccountSO;
import so.EditMealOfferSO;
import so.GetAllOrdersForMealOfferSO;
import so.GetEmployeeOrderSO;
import so.GetEmployeesSO;
import so.GetMealOfferSO;
import so.GetMealOffersSO;
import so.GetMealsSO;
import so.LoginSO;
import so.SaveMealOfferSO;
import so.SaveOrderSO;

/**
 *
 * @author Bane
 */
public class Controller {

    public Controller() {
    }

    public Account login(String username, String password) throws Exception {
        Account account = new Account(username, password);
        AbstractSO loginSo = new LoginSO();
        Account loggedInAccount = (Account) loginSo.execute(account);
        return loggedInAccount;
    }

    public List<Employee> getEmployees() throws Exception {
        AbstractSO getEmployeesSO = new GetEmployeesSO();
        List<Employee> employees = (List<Employee>) getEmployeesSO.execute(null);
        return employees;
    }

    public void createAcoount(Account account) throws Exception {
        AbstractSO createAccountSO = new CreateAccountSO();
        createAccountSO.execute(account);
    }

    public void deactivateAccount(Account account) throws Exception {
        AbstractSO deactivateAccountSO = new DeactivateAccountSO();
        deactivateAccountSO.execute(account);
    }

    public List<Meal> getMeals() throws Exception {
        AbstractSO getMealsSo = new GetMealsSO();
        return (List<Meal>) getMealsSo.execute(null);
    }

    public List<MealOffer> getMealOffers() throws Exception {
        AbstractSO getMealsOffersSo = new GetMealOffersSO();
        return (List<MealOffer>) getMealsOffersSo.execute(null);
    }

    public MealOffer getMealOffer(long mealId) throws Exception {
        AbstractSO getMealsOffersSo = new GetMealOfferSO();
        return (MealOffer) getMealsOffersSo.execute(mealId);
    }

    public void saveMealOffer(MealOffer mealOffer) throws Exception {
        AbstractSO saveMealOfferSO = new SaveMealOfferSO();
        saveMealOfferSO.execute(mealOffer);
    }

    public void editMealOffer(MealOffer mealOffer) throws Exception {
        AbstractSO editMealOfferSO = new EditMealOfferSO();
        editMealOfferSO.execute(mealOffer);
    }

    public void saveOrder(Order order) throws Exception {
        AbstractSO saveOrderSO = new SaveOrderSO();
        saveOrderSO.execute(order);
    }

    public Order getEmployeeOrder(Order order) throws Exception {
        AbstractSO getEmployeeOrderSO = new GetEmployeeOrderSO();
        return (Order) getEmployeeOrderSO.execute(order);
    }

    public List<Order> getAllOrders(MealOffer mealOffer) throws Exception {
        AbstractSO getAllOrdersSO = new GetAllOrdersForMealOfferSO();
        return (List<Order>) getAllOrdersSO.execute(mealOffer);
    }

    public String createGroupOrder(MealOffer mealOffer) throws Exception {
        AbstractSO createGroupOrderSO = new CreateGroupOrderSO();
        return (String) createGroupOrderSO.execute(mealOffer);
    }

}
