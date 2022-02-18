/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import exception.ValidationException;
import model.Order;
import persistence.db.repository.OrderedMealRepository;
import validation.Validator;

/**
 *
 * @author Bane
 */
public class SaveOrderSO extends AbstractSO{
    private final OrderedMealRepository orderedMealRepository;
    public SaveOrderSO() {
        this.orderedMealRepository = new OrderedMealRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Order)) {
            throw new Exception("Parametar mora biti instanca klase Employee.");
        }
        Order order = (Order) param;
        try {
            Validator.startValidation()
                    .validateNotNull(order.getAccount(), "Nalog ne sme biti null")
                    .validateNotNull(order.getMealOffer(), "Ponuda jela ne sme biti null")
                    .validateNotNull(order.getOrderedMeals(), "Lista jela ne sme biti null")
                    .validateID(order.getAccount().getId(), "Identifikator naloga mora biti pozitivan ceo broj")
                    .validateID(order.getMealOffer().getId(), "Identifikator ponude jela mora biti pozitivan ceo broj")
                    .throwIfInvalide();
        } catch (ValidationException e) {
            throw e;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        Order order = (Order)param;
        this.orderedMealRepository.delete(order);
        this.orderedMealRepository.add(order);
        return null;
    }
}
