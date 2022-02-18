/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import java.util.List;
import model.Account;
import model.MealOffer;
import persistence.db.repository.MealOfferRepository;

/**
 *
 * @author Bane
 */
public class GetMealOffersSO extends AbstractSO{
    private final MealOfferRepository mealOfferRepository;

    public GetMealOffersSO() {
        this.mealOfferRepository = new MealOfferRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        List<MealOffer> mealOffers = this.mealOfferRepository.getAll();
        
        return mealOffers;
    }    
}
