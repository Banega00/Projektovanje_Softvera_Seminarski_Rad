package so;

import java.util.List;
import model.Meal;
import persistence.db.repository.AccountRepository;
import persistence.db.repository.MealRepository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bane
 */
public class GetMealsSO extends AbstractSO{
    private final MealRepository mealRepository;

    public GetMealsSO() {
        this.mealRepository = new MealRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        List<Meal> mealList = (List<Meal>) this.mealRepository.getAll();
        return mealList;
    }
    
}
