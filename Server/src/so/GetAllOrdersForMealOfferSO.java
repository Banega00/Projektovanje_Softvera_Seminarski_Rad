/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import exception.ValidationException;
import model.MealOffer;
import model.Order;
import persistence.db.repository.OrderedMealRepository;
import validation.Validator;

/**
 *
 * @author Bane
 */
public class GetAllOrdersForMealOfferSO extends AbstractSO {

    private final OrderedMealRepository orderedMealRepository;

    public GetAllOrdersForMealOfferSO() {
        this.orderedMealRepository = new OrderedMealRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof MealOffer)) {
            //TODO IZMENI PORUKU
            throw new Exception("Parametar mora biti instanca klase MealOffer");
        }

        MealOffer mo = (MealOffer) param;
        try {
            Validator.startValidation().
                    validateID(mo.getId(), "Identifikator mora biti pozitivan ceo broj")
                    .validateDate(mo.getDate(), "Datum ne sme biti u prošlosti").throwIfInvalide();
        } catch (ValidationException e) {
            throw e;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        return this.orderedMealRepository.getAllOrderForMealOffer((MealOffer) param);
    }
}
