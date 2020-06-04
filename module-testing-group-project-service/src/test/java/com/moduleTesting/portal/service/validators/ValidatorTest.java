package com.moduleTesting.portal.service.validators;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexey Druzik on 04.06.2020
 */
public class ValidatorTest {

    Validator validator = new Validator();

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before ValidatorTest.class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After ValidatorTest.class");
    }

    @BeforeEach
    public void initTest() {
        validator = new Validator();
    }

    @AfterEach
    public void afterTest() {
        validator = null;
    }

    @Test
    public void TestIsPatientLastNameValidValid() {
        final String lastname = "Иванов";
        final boolean expectedResult = true;
        final boolean actualResult = validator.isUserLastNameValid(lastname);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void TestIsPatientLastNameValidTooLong() {
        final String lastname = "Ращвюфывлфюыаыпбчсмьчбпаомваьмвтапаписаи";
        final boolean expectedResult = false;
        final boolean actualResult = validator.isUserLastNameValid(lastname);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void TestIsPatientLastNameValidTooShort() {
        final String lastname = "И";
        final boolean expectedResult = false;
        final boolean actualResult = validator.isUserLastNameValid(lastname);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void TestIsPatientLastNameValidEmpty() {
        final String lastname = "";
        final boolean expectedResult = false;
        final boolean actualResult = validator.isUserLastNameValid(lastname);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void TestIsPatientLastNameNull() {
        final String lastname = null;
        final boolean expectedResult = false;
        final boolean actualResult = validator.isUserLastNameValid(lastname);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void TestIsPatientNameValidValid() {
        final String name = "Иван";
        final boolean expectedResult = true;
        final boolean actualResult = validator.isUserNameValid(name);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void TestIsPatientNameValidTooLong() {
        final String name = "Фпирпатпртнрткемвымпваипень";
        final boolean expectedResult = false;
        final boolean actualResult = validator.isUserNameValid(name);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void TestIsPatientNameValidTooShort() {
        final String name = "И";
        final boolean expectedResult = false;
        final boolean actualResult = validator.isUserNameValid(name);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void TestIsPatientNameValidEmpty() {
        final String name = "";
        final boolean expectedResult = false;
        final boolean actualResult = validator.isUserNameValid(name);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void TestIsPatientNameValidNull() {
        final String name = null;
        final boolean expectedResult = false;
        final boolean actualResult = validator.isUserNameValid(name);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void TestIsBirthDateValidValid() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2);
        final Date date = cal.getTime();
        final boolean expectedResult = true;
        final boolean actualResult = validator.isBirthDateValid(date);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void TestIsBirthDateValidFutureDate() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 10);
        final Date date = cal.getTime();
        final boolean expectedResult = false;
        final boolean actualResult = validator.isBirthDateValid(date);
        assertEquals(expectedResult, actualResult);
    }
}
