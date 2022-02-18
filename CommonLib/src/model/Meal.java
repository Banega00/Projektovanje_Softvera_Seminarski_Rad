/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import model.enums.MealType;

/**
 *
 * @author Bane
 */
public class Meal extends BaseModel implements Serializable{
    private long id;
    private MealType type;
    private String name;
    private double price;
    private Currency currency;
    private int numberOfPortions;
    
    public Meal(long id, MealType type, String name, double price, Currency currency) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.currency = currency;
    }
    
    public Meal(long id, MealType type, String name, double price, Currency currency,int numberOfPortions) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.currency = currency;
    }
    
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MealType getType() {
        return type;
    }

    public void setType(MealType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfPortions() {
        return numberOfPortions;
    }

    public void setNumberOfPortions(int numberOfPortions) {
        this.numberOfPortions = numberOfPortions;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Meal other = (Meal) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    @Override
    public String getAttributeList() {
        return "id, meal_type, name, price, currency_id";
    }

    @Override
    public String getClassName() {
        return "account";
    }

    @Override
    public String getAttributeValues() {
        return "'" + this.id + "', '" + this.type.toString() + "', '" + this.name + "', '" + this.price + "', " + this.currency.getId();
    }

    @Override
    public String getQueryCondition() {
        return "id=" + id;
    }
}