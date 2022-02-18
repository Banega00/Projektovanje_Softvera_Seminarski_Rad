/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Bane
 */
public class Order implements Serializable{
    private MealOffer mealOffer;
    private Account account;
    private List<Meal> orderedMeals;

    public Order(MealOffer mealOffer, Account account, List<Meal> orderedMeals) {
        this.mealOffer = mealOffer;
        this.account = account;
        this.orderedMeals = orderedMeals;
    }

    public List<Meal> getOrderedMeals() {
        return orderedMeals;
    }

    public void setOrderedMeals(List<Meal> orderedMeals) {
        this.orderedMeals = orderedMeals;
    }

    public MealOffer getMealOffer() {
        return mealOffer;
    }

    public void setMealOffer(MealOffer mealOffer) {
        this.mealOffer = mealOffer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
}
