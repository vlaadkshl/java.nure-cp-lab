package ua.nure.cp.lab_first.oop;

import java.util.Arrays;
import java.util.Collection;

public class ComplexNumber {
    private double re, im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public ComplexNumber() {
        this(0, 1);
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    /**
     * @param a First <code>ComplexNumber</code> element
     * @param b Second <code>ComplexNumber</code> element
     * @return Sum of two elements of <code>ComplexNumber</code> type
     */
    public static final ComplexNumber add(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.re + b.re, a.im + b.im);
    }

    /**
     * @param a First <code>ComplexNumber</code> element
     * @param b Second <code>ComplexNumber</code> element
     * @return Product of two elements of <code>ComplexNumber</code> type
     */
    public static final ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.re * b.re - a.im * b.im, a.im * b.re + a.re * b.im);
    }

    /**
     * If sum is available, returns sum of <code>ComplexNumber</code> type,
     * otherwise returns <code>null</code>
     * 
     * @param array An array of <code>ComplexNumber</code> elements
     * @return Sum of the array elements
     */
    public static final ComplexNumber addNumbers(ComplexNumber[] array) {
        return Arrays.stream(array).reduce(ComplexNumber::add).orElse(null);
    }

    /**
     * If sum is available, returns sum of <code>ComplexNumber</code> type,
     * otherwise returns <code>null</code>
     * 
     * @param set A <code>Collection</code> of <code>ComplexNumber</code> elements
     * @return Sum of the set elements
     */
    public static final ComplexNumber addNumbers(Collection<ComplexNumber> set) {
        return set.stream().reduce(ComplexNumber::add).orElse(null);
    }

    /**
     * If product is available, returns product of <code>ComplexNumber</code> type,
     * otherwise returns <code>null</code>
     * 
     * @param array An array of <code>ComplexNumber</code> elements
     * @return Product of the array elements
     */
    public static final ComplexNumber multiplyNumbers(ComplexNumber[] array) {
        return Arrays.stream(array).reduce(ComplexNumber::multiply).orElse(null);
    }

    /**
     * If product is available, returns product of <code>ComplexNumber</code> type,
     * otherwise returns <code>null</code>
     * 
     * @param set A <code>Collection</code> of <code>ComplexNumber</code> elements
     * @return Product of the set elements
     */
    public static final ComplexNumber multiplyNumbers(Collection<ComplexNumber> set) {
        return set.stream().reduce(ComplexNumber::multiply).orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ComplexNumber))
            return false;

        ComplexNumber compl = (ComplexNumber) o;

        return Double.compare(compl.re, re) == 0 && Double.compare(compl.im, im) == 0;
    }

    @Override
    public int hashCode() {
        return (int) re * 100 + (int) im * 100;
    }

    @Override
    public String toString() {
        return (im >= 0)
                ? "( " + re + " + " + im + "i )"
                : "( " + re + " - " + im * (-1) + "i )";
    }
}
