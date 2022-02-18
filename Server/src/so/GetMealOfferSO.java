/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import exception.ValidationException;
import java.util.List;
import model.MealOffer;
import model.Order;
import persistence.db.repository.MealOfferRepository;
import validation.Validator;

/**
 *
 * @author Bane
 */
public class GetMealOfferSO extends AbstractSO{
    private final MealOfferRepository mealOfferRepository;

    public GetMealOfferSO() {
        this.mealOfferRepository = new MealOfferRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Long)) {
            //TODO IZMENI PORUKU
            throw new Exception("Parametar mora biti tipa long");
        }
        
        long id = (long) param;
        
        try {
            Validator.startValidation()
                    .validateID(id, "Identifikator mora biti pozitivan ceo broj").throwIfInvalide();
        } catch (ValidationException e) {
            throw e;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        MealOffer mealOffer = this.mealOfferRepository.getById((long)param);
        return mealOffer;
    }    
    
}
