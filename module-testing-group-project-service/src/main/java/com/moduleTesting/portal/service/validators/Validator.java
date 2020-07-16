package com.moduleTesting.portal.service.validators;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alexey Druzik on 04.06.2020
 */
public class Validator {

    public static boolean isUserLastNameValid(String lastName) {
        if (lastName == null) {
            return false;
        }
        if (lastName.trim().length() < 4 || lastName.trim().length() > 25) {
            return false;
        }
        Pattern pattern = Pattern.compile("[A-Za-z0-9_-]{4,25}$");
        Matcher mat = pattern.matcher(lastName);
        if(!mat.matches()){
            return false;
        }

        return true;
    }

    public static boolean isUserNameValid(String name) {
        if (name == null) {
            return false;
        }
        if (name.trim().length() < 4 || name.trim().length() > 25) {
            return false;
        }
        Pattern pattern = Pattern.compile("[A-Za-z0-9_-]{4,25}$");
        Matcher mat = pattern.matcher(name);
        if(!mat.matches()){
            return false;
        }

        return true;
    }

    public static boolean isBirthDateValid(Date date) {

        if (date.after(new Date())) {
            return false;
        }
        return true;
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);

        if(mat.matches()){
            return true;
        }

        return false;
    }

}
