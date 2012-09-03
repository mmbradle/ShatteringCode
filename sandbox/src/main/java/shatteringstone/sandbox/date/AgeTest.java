package shatteringstone.sandbox.date;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class AgeTest {

    @Test
    public void testCalcAgeBeforeBDay() {
        Calendar birthDay = new GregorianCalendar(1980, 1, 1);
        assertEquals(32, Age.calcAge(birthDay));
    }
    @Test
    public void testCalcAgeAfterBDay() {
        Calendar birthDay = new GregorianCalendar(1980, 12, 31);
        assertEquals(31, Age.calcAge(birthDay));
    }

}
