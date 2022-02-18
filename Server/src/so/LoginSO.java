/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import exception.ValidationException;
import java.util.List;
import model.Account;
import persistence.db.repository.AccountRepository;
import validation.Validator;

/**
 *
 * @author Bane
 */
public class LoginSO extends AbstractSO{
    private final AccountRepository accountRepository;

    public LoginSO() {
        this.accountRepository = new AccountRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Account)) {
            //TODO IZMENI PORUKU
            throw new Exception("Parametar mora biti instanca klase Account");
        }
        Account account = (Account) param;
        
        try {
            Validator.startValidation()
                    .validateNotNullOrEmpty(account.getUsername(), "Korisničko ime je obavezno.")
                    .validateNotNullOrEmpty(account.getPassword(), "Šifra je obavezna.").throwIfInvalide();
        } catch (ValidationException e) {
            throw e;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        Account requestAccount = (Account) param;
        Account account = this.accountRepository.getForLogin(requestAccount.getUsername(), requestAccount.getPassword());
        if(account == null) throw new Exception("Nevalidno korisničko ime ili šifra");
        if(!account.isActive()) throw new Exception("Nalog je dekativiran");
        return account;
    }
}
