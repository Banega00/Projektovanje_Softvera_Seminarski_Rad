/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import exception.ValidationException;
import model.Account;
import model.Order;
import persistence.db.repository.OrderedMealRepository;
import validation.Validator;

/**
 *
 * @author Bane
 */
public class GetEmployeeOrderSO extends AbstractSO{

    private final OrderedMealRepository orderedMealRepository;

    public GetEmployeeOrderSO() {
        this.orderedMealRepository = new OrderedMealRepository();
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Order)) {
            //TODO IZMENI PORUKU
            throw new Exception("Proslijeđeni parametar nije validan. Potrebno je proslijediti objekat klase Account.");
        }
        
        Order order = (Order) param;
        
        try {
            Validator.startValidation()
                    .validateNotNull(order.getAccount(), "Nalog ne sme biti null")
                    .validateNotNull(order.getMealOffer(), "Ponuda jela ne sme biti null").throwIfInvalide();
        } catch (ValidationException e) {
            throw e;
        }
    }

    

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        return this.orderedMealRepository.getOrderedMeals((Order)param);
    }
}
