package ua.nure.cp.lab_first.oop;

import java.lang.reflect.Array;
import java.util.List;

public class Fraction {
    private final ComplexNumber m, n;

    public Fraction(ComplexNumber m, ComplexNumber n) throws Exception {
        if (n.getRe() == 0 && n.getIm() == 0)
            throw new Exception("Denominator of fraction == 0");

        this.m = m;
        this.n = n;
    }

    public Fraction(double m_re, double m_im, double n_re, double n_im) throws Exception {
        if (n_re == 0 && n_im == 0)
            throw new Exception("Denominator of fraction == 0");

        this.m = new ComplexNumber(m_re, m_im);
        this.n = new ComplexNumber(n_re, n_im);
    }

    /**
     * Modifies an array by summing each even element and the subsequent odd
     * element.
     * 
     * @param array <code>Fraction</code> array
     */
    public static void sumFractionArray(Fraction[] array) {
        for (int i = 1; i < Array.getLength(array) - 1; i += 2) {
            try {
                Fraction current = array[i], next = array[i + 1];

                ComplexNumber first = ComplexNumber.multiply(current.m, next.n),
                        second = ComplexNumber.multiply(next.m, current.n),

                        numerator = ComplexNumber.add(first, second),
                        denominator = ComplexNumber.multiply(current.n, next.n);

                array[i] = new Fraction(numerator, denominator);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Modifies an array by summing each even element and the subsequent odd
     * element.
     * 
     * @param array <code>Fraction</code> Collection
     */
    public static void sumFractionArray(List<Fraction> array) {
        for (int i = 1; i < array.size() - 1; i += 2) {
            try {
                Fraction current = array.get(i), next = array.get(i + 1);

                ComplexNumber first = ComplexNumber.multiply(current.m, next.n),
                        second = ComplexNumber.multiply(next.m, current.n),

                        numerator = ComplexNumber.add(first, second),
                        denominator = ComplexNumber.multiply(current.n, next.n);

                array.set(i, new Fraction(numerator, denominator));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public String toString() {
        return m + " / " + n;
    }
}
