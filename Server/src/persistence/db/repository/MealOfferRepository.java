/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Currency;
import model.Meal;
import model.MealOffer;
import model.enums.MealType;
import persistence.db.DBConnectionFactory;
import persistence.db.DBRepository;

/**
 *
 * @author Bane
 */
public class MealOfferRepository implements DBRepository<MealOffer, Long>{
    private Connection connection;

    @Override
    public List<MealOffer> getAll() throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM MEAL_OFFER m JOIN ACCOUNT a ON m.account_id=a.id ORDER BY m.date DESC;");
        ResultSet rs = ps.executeQuery();
        
        List<MealOffer> meallOffers = new ArrayList<>();
        while(rs.next()){
            MealOffer m = new MealOffer(rs.getLong("m.id"), rs.getDate("m.date"), new Account(rs.getLong("a.id"),rs.getString("a.username"),rs.getString("a.password"),rs.getBoolean("a.isAdmin"),rs.getBoolean("a.active"),null));
            meallOffers.add(m);
        }
        rs.close();
        ps.close();
        
        return meallOffers;
    }

    @Override
    public void add(MealOffer mo) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("INSERT INTO MEAL_OFFER(date,account_id) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS);
        ps.setDate(1, new Date(mo.getDate().getTime()));
        ps.setLong(2, mo.getAccount().getId());
        
        int affectedRows = ps.executeUpdate();
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if(generatedKeys.next()){
            mo.setId(generatedKeys.getLong(1));
        }else{
            throw new Exception("Neuspešno čuvanje ponude jela");
        }
        ps = this.connection.prepareStatement("INSERT INTO OFFERED_MEAL VALUES(?,?);");
        for(Meal m: mo.getMeals()){
            ps.setLong(1, m.getId());
            ps.setLong(2, mo.getId());
            ps.executeUpdate();
        }
        ps.close();
    }

    @Override
    public void edit(MealOffer t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MealOffer t) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("DELETE FROM MEAL_OFFER WHERE id=?;");
        ps.setLong(1, t.getId());
        ps.executeUpdate();
        
        ps.close();
    }

    @Override
    public MealOffer getById(Long id) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM MEAL_OFFER mo LEFT JOIN OFFERED_MEAL om ON mo.id=om.meal_offer_id LEFT JOIN MEAL m ON m.id=om.meal_id LEFT JOIN CURRENCY c ON c.id=m.currency_id LEFT JOIN ACCOUNT a ON a.id=mo.account_id WHERE mo.id=?;");
        ps.setLong(1, id);
        System.out.println(id);
        ResultSet rs = ps.executeQuery();
        MealOffer mo = null;
        List<Meal> offeredMeals = new ArrayList<>();
        while(rs.next()){
            if(mo==null){
                mo = new MealOffer(rs.getLong("mo.id"), rs.getDate("mo.date"), new Account(rs.getLong("a.id"),rs.getString("a.username"),rs.getString("a.password"),rs.getBoolean("a.isAdmin"),rs.getBoolean("a.active"),null));
                mo.setMeals(offeredMeals);
            }
            if(rs.getLong("m.id")>0)offeredMeals.add(new Meal(rs.getLong("m.id"), MealType.valueOf(rs.getString("m.meal_type")) , rs.getString("name"), rs.getDouble("m.price"), new Currency(rs.getLong("c.id"), rs.getString("c.name"), rs.getString("c.shortName"))));
        }
        rs.close();
        ps.close();
        
        return mo;
    }
}
