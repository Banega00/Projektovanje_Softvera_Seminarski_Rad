/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Currency;
import model.Employee;
import model.Meal;
import model.enums.MealType;
import persistence.db.DBConnectionFactory;
import persistence.db.DBRepository;

/**
 *
 * @author Bane
 */
public class MealRepository implements DBRepository<Meal, Long>{
    private Connection connection;

    @Override
    public List<Meal> getAll() throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM MEAL JOIN CURRENCY c ON c.id=currency_id;");
        ResultSet rs = ps.executeQuery();
        
        List<Meal> mealList = new ArrayList<>();
        while(rs.next()){
            Meal m = new Meal(rs.getLong("id"), MealType.valueOf(rs.getString("meal_type")), rs.getString("name"), rs.getDouble("price"), new Currency(rs.getLong("c.id"), rs.getString("c.name"), rs.getString("c.shortName")));
            mealList.add(m);
        }
        rs.close();
        ps.close();
        
        return mealList;
    }

    @Override
    public void add(Meal t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Meal t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Meal t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Meal getById(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
