/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import exception.ValidationException;
import model.Account;
import persistence.db.repository.AccountRepository;
import validation.Validator;

/**
 *
 * @author Bane
 */
public class DeactivateAccountSO extends AbstractSO{
    
    private final AccountRepository accountRepository;

    public DeactivateAccountSO() {
        this.accountRepository = new AccountRepository();
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Account)) {
            throw new Exception("Parametar mora biti instanca klase Account");
        }
        Account account = (Account) param;
        
        try {
            Validator.startValidation()
                    .validateID(account.getId(), "Identifikator mora biti pozitivan ceo broj").throwIfInvalide();
        } catch (ValidationException e) {
            throw e;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        Account acc = (Account) param;
        acc.setActive(false);
        this.accountRepository.edit((Account)param);
        return null;
    }
}
