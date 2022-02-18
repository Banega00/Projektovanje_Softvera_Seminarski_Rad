/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import exception.ValidationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Bane
 */
public class Validator {

    private final List<String> validationErros;

    private Validator() {
        validationErros = new ArrayList();
    }

    public static Validator startValidation() {
        return new Validator();
    }

    public Validator validateID(long id, String errorMessage) {
        if (id <= 0) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateNotNull(Object o, String errorMessage) throws ValidationException {
        if (o == null) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateNotNullOrEmpty(String value, String errorMessage) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateFormat(String value, String format, String errorMessage) throws ValidationException {
        Pattern p = Pattern.compile(format);
        Matcher m = p.matcher(value);
        boolean b = m.matches();
        if (value == null || !b) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateDate(Date date, String errorMessage) {
        if (date.before(new Date())) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public void throwIfInvalide() throws ValidationException {
        if (!validationErros.isEmpty()) {
            throw new ValidationException(this.validationErros.stream().collect(Collectors.joining("\n")));
        }
    }

}
