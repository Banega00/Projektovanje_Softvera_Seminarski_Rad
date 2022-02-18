/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Bane
 */
public class MealOffer extends BaseModel implements Serializable{
    private long id;
    private Date date;
    private Account account;
    private List<Meal> meals;

    public MealOffer(long id, Date date, Account account) {
        this.id = id;
        this.date = date;
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
    
    @Override
    public String getAttributeList() {
        return "id, date, account_id";
    }

    @Override
    public String getClassName() {
        return "meal_offer";
    }

    @Override
    public String getAttributeValues() {
        return "'" + this.id + "', '" + this.date + "', '" + this.account.getId();
    }

    @Override
    public String getQueryCondition() {
        return "id=" + id;
    }
}
