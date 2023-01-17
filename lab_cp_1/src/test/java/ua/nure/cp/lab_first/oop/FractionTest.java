package ua.nure.cp.lab_first.oop;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class FractionTest {
    @Test
    public void fractionTest() {
        try {
            ArrayList<Fraction> array = new ArrayList<Fraction>(Arrays.asList(
                    new Fraction(1, 1, 2, -1),
                    new Fraction(-1, 2, 4, -3),
                    new Fraction(-5, 8, 1, -1),
                    new Fraction(1, 9, 4, -1.5),
                    new Fraction(-2, -3, -4, 0.5)));

            Fraction.sumFractionArray(array);

            assertEquals(new Fraction(5, 50, 1, -7).toString(), array.get(1).toString());

            assertEquals(new Fraction(-21, -44.5, -15.25, 8).toString(), array.get(3).toString());
        } catch (Exception e) {
        }

    }
}
