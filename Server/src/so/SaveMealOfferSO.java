/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import exception.ValidationException;
import model.Meal;
import model.MealOffer;
import persistence.db.repository.MealOfferRepository;
import validation.Validator;

/**
 *
 * @author Bane
 */
public class SaveMealOfferSO extends AbstractSO{
    private final MealOfferRepository mealOfferRepository;
    public SaveMealOfferSO() {
        this.mealOfferRepository = new MealOfferRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof MealOffer)) {
            throw new Exception("Parametar mora biti instanca klase MealOffer");
        }
        
        MealOffer mo = (MealOffer) param;
        try {
            Validator.startValidation()
                    .validateNotNull(mo.getDate(), "Datum je obavezan")
                    .validateDate(mo.getDate(), "Datum ne sme biti u prošlosti").throwIfInvalide();
        } catch (ValidationException e) {
            throw e;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        MealOffer mo = (MealOffer)param;
        this.mealOfferRepository.add(mo);
        return null;
    }
}
