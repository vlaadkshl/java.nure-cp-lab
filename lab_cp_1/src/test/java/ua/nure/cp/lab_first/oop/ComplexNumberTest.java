package ua.nure.cp.lab_first.oop;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class ComplexNumberTest {
    @Test
    public void addComplexSet() {
        try {
            ArrayList<ComplexNumber> array = new ArrayList<ComplexNumber>(Arrays.asList(
                    new ComplexNumber(1, 1),
                    new ComplexNumber(-5, 1.5),
                    new ComplexNumber(8.5, -8),
                    new ComplexNumber(7, 4)));

            assertEquals(new ComplexNumber(11.5, -1.5), ComplexNumber.addNumbers(array));
        } catch (Exception e) {
        }
    }

    @Test
    public void multiplyComplexSet() {
        try {
            ArrayList<ComplexNumber> array = new ArrayList<ComplexNumber>(Arrays.asList(
                    new ComplexNumber(1, 1),
                    new ComplexNumber(8.5, -8),
                    new ComplexNumber(7, 4),
                    new ComplexNumber(-5, 1.5)));

            assertEquals(new ComplexNumber(-671.75, -177.25), ComplexNumber.multiplyNumbers(array));
        } catch (Exception e) {
        }
    }
}
