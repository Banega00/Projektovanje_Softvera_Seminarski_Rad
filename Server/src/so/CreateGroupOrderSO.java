/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import java.util.ArrayList;
import java.util.List;
import model.Meal;
import model.MealOffer;
import model.Order;
import persistence.db.repository.OrderedMealRepository;

/**
 *
 * @author Bane
 */
public class CreateGroupOrderSO extends AbstractSO {

    private final OrderedMealRepository orderedMealRepository;

    public CreateGroupOrderSO() {
        this.orderedMealRepository = new OrderedMealRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof MealOffer)) {
            throw new Exception("Parametar mora biti instanca klase MealOffer");
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        MealOffer mealOffer = (MealOffer) param;
        List<Order> orders = this.orderedMealRepository.getAllOrderForMealOffer(mealOffer);
        String groupOrder = "Grupna porudžbina za dan " + mealOffer.getDate() + "\n";
        groupOrder += "_________________________________________________________________________\n";
        groupOrder += "Jela:\n";
        groupOrder += "_________________________________________________________________________\n";
        List<Meal> distinctMeals = new ArrayList<>();
        for (Order o : orders) {
            for (Meal m : o.getOrderedMeals()) {
                if (distinctMeals.contains(m)) {
                    continue;
                }
                m.setNumberOfPortions(this.mealTotalPortions(m, orders));
                distinctMeals.add(m);
            }
        }

        groupOrder+=String.format("%-25s %-15s %-20s %-19s\n", "Jelo", "Broj porcija", "Cena", "Ukupna cena");
        double totalPrice = 0;
        for (Meal m : distinctMeals) {
            totalPrice += m.getPrice() * m.getNumberOfPortions();
            groupOrder+=String.format("%-25s %-15d %.2f%-15s %.2f%-15s\n", m.getName(), m.getNumberOfPortions(), m.getPrice(), m.getCurrency().getShortname(), m.getPrice() * m.getNumberOfPortions(), m.getCurrency().getShortname());
        }

        groupOrder += "_________________________________________________________________________\n";
        groupOrder +="Ukupna cena porudžbine: " + totalPrice;
        return groupOrder;
    }

    public int mealTotalPortions(Meal m, List<Order> orders) {
        int i = 0;
        for (Order o : orders) {
            for (Meal meal : o.getOrderedMeals()) {
                if (m.equals(meal)) {
                    i += meal.getNumberOfPortions();
                }
            }
        }
        return i;
    }
}
