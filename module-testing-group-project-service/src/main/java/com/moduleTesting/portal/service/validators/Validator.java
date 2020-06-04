package com.moduleTesting.portal.service.validators;

import java.util.Date;

/**
 * @author Alexey Druzik on 04.06.2020
 */
public class Validator {

    public boolean isUserLastNameValid(String lastName) {
        if (lastName == null) {
            return false;
        }
        if (lastName.trim().length() < 3 || lastName.trim().length() > 30) {
            return false;
        }
        return true;
    }

    public boolean isUserNameValid(String name) {
        if (name == null) {
            return false;
        }
        if (name.trim().length() < 2 || name.trim().length() > 15) {
            return false;
        }
        return true;
    }

    public boolean isBirthDateValid(Date date) {

        if (date.after(new Date())) {
            return false;
        }
        return true;
    }

}
