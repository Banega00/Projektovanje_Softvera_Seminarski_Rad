/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Currency;
import model.Employee;
import model.Meal;
import model.MealOffer;
import model.Order;
import model.enums.MealType;
import persistence.db.DBConnectionFactory;
import persistence.db.DBRepository;

/**
 *
 * @author Bane
 */
public class OrderedMealRepository implements DBRepository<Order, Long> {

    private Connection connection;

    @Override
    public List<Order> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Order order) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("INSERT INTO ordered_meal VALUES(?,?,?,?)");
        for (Meal m : order.getOrderedMeals()) {
            if (m == null) {
                continue;
            };

            ps.setLong(1, order.getAccount().getId());
            ps.setLong(2, order.getMealOffer().getId());
            ps.setLong(3, m.getId());
            ps.setInt(4, m.getNumberOfPortions());

            ps.executeUpdate();
        }

        ps.close();
    }

    @Override
    public void edit(Order t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Order order) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("DELETE FROM ORDERED_MEAL WHERE meal_offer_id=? AND account_id=?");
        ps.setLong(1, order.getMealOffer().getId());
        ps.setLong(2, order.getAccount().getId());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public Order getById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Order getOrderedMeals(Order order) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM ORDERED_MEAL om INNER JOIN MEAL m ON om.meal_id=m.id INNER JOIN CURRENCY c ON c.id=m.currency_id WHERE om.meal_offer_id=? AND account_id=?;");
        ps.setLong(1, order.getMealOffer().getId());
        ps.setLong(2, order.getAccount().getId());
        ResultSet rs = ps.executeQuery();

        List<Meal> orderedMeals = new ArrayList<>();
        while (rs.next()) {
            Meal m = new Meal(rs.getLong("m.id"), MealType.valueOf(rs.getString("m.meal_type")), rs.getString("m.name"), rs.getDouble("m.price"), new Currency(rs.getLong("c.id"), rs.getString("c.name"), rs.getString("c.shortName")));
            m.setNumberOfPortions(rs.getInt("om.number_of_portions"));
            orderedMeals.add(m);
        }
        order.setOrderedMeals(orderedMeals);
        return order;
    }

    public List<Order> getAllOrderForMealOffer(MealOffer mealOffer) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM ORDERED_MEAL om INNER JOIN MEAL m ON om.meal_id=m.id INNER JOIN CURRENCY c ON c.id=m.currency_id INNER JOIN ACCOUNT a ON a.id=om.account_id INNER JOIN EMPLOYEE e ON e.id=a.employeeId WHERE om.meal_offer_id=? ORDER BY a.id;");
        ps.setLong(1, mealOffer.getId());
        ResultSet rs = ps.executeQuery();

        List<Order> orders = new ArrayList<>();
        long prevAccountId = 0l;
        Account prevAccount = null;
        List<Meal> orderedMeals = new ArrayList<>();
        while (rs.next()) {
            long currentAccountId = rs.getLong("a.id");
            if (prevAccountId != currentAccountId && prevAccount != null) {
                Order order = new Order(mealOffer, prevAccount, orderedMeals);
                orders.add(order);

                orderedMeals = new ArrayList<>();
            }

            prevAccount = new Account(rs.getLong("a.id"), rs.getString("a.username"), rs.getString("a.username"), false, rs.getBoolean("a.active"), new Employee(rs.getLong("e.id"), rs.getString("e.ImePrezime"), rs.getString("JMBG"), rs.getString("email")));
            Meal m = new Meal(rs.getLong("m.id"), MealType.valueOf(rs.getString("m.meal_type")), rs.getString("m.name"), rs.getDouble("m.price"), new Currency(rs.getLong("c.id"), rs.getString("c.name"), rs.getString("c.shortName")));
            m.setNumberOfPortions(rs.getInt("om.number_of_portions"));
            orderedMeals.add(m);
            prevAccountId = prevAccount.getId();
        }
        if (prevAccount != null) {
            Order order = new Order(mealOffer, prevAccount, orderedMeals);
            orders.add(order);
        }
        return orders;
    }
}
