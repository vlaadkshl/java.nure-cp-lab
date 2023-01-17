package ua.nure.cp.lab_first.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
        private static Scanner sc = new Scanner(System.in);

        private static double setDouble(String msg) {
                double n = Double.NaN;

                while (Double.compare(n, Double.NaN) == 0) {
                        System.out.print(msg + " = ");
                        String n_ = sc.nextLine();

                        if (n_.isBlank()) {
                                System.out.println("n is undefined: Cannot find n in blank string. Try again...");
                        } else {
                                try {
                                        n = Double.parseDouble(n_);
                                } catch (NumberFormatException e) {
                                        System.out.println("n is undefined: Cannot find n in string " + n_
                                                        + ". Try again...");
                                }
                        }
                }

                return n;
        }

        private static ComplexNumber setComplexNumber() {
                double re = setDouble("Real part");
                double im = setDouble("Imagine part");
                while (im == 0) {
                        System.out.println("Imagine part == 0. Try again");
                        im = setDouble("Imagine part");
                }

                return new ComplexNumber(re, im);
        }

        private static Fraction setFraction() {
                System.out.println("NUMERATOR:");
                double re_num = setDouble("Real part");
                double im_num = setDouble("Imagine part");
                while (im_num == 0) {
                        System.out.println("Imagine part == 0. Try again");
                        im_num = setDouble("Imagine part");
                }

                System.out.println("DENOMINATOR:");
                double re_denom = setDouble("Real part");
                while (re_denom == 0) {
                        System.out.println("Real part of denominator == 0. Try again");
                        re_denom = setDouble("Real part");
                }
                double im_denom = setDouble("Imagine part");
                while (im_denom == 0) {
                        System.out.println("Imagine part == 0. Try again");
                        im_denom = setDouble("Imagine part");
                }

                try {
                        return new Fraction(re_num, im_num, re_denom, im_denom);
                } catch (Exception e) {
                        System.out.println(e);
                        return null;
                }
        }

        private static int setArraySize() {
                int n = 0;

                while (n <= 0) {
                        System.out.print("Enter n: ");
                        String n_ = sc.nextLine();

                        if (n_.isBlank()) {
                                System.out.println("n is undefined: Cannot find n in blank string. Try again...");
                        } else {
                                try {
                                        n = Integer.parseInt(n_);

                                        if (n <= 0)
                                                System.out.println("n must be > 0. Try again...");
                                } catch (NumberFormatException e) {
                                        System.out.println("n is undefined: Cannot find n in string " + n_
                                                        + ". Try again...");
                                }
                        }
                }

                return n;
        }

        public static void main(String[] args) {
                System.out.println("===COMPLEX NUMBERS===");

                int n_complex = setArraySize();
                System.out.println();

                ComplexNumber[] complexArray = new ComplexNumber[n_complex];

                for (int i = 0; i < n_complex; i++) {
                        System.out.println(i + 1 + " element...\n");

                        complexArray[i] = setComplexNumber();

                        System.out.println("Added: " + complexArray[i] + "\n");
                }

                // Копіювання масивів
                ArrayList<ComplexNumber> complexList = new ArrayList<>(List.of(complexArray));
                HashSet<ComplexNumber> complexSet = new HashSet<>(complexList);

                // Сумми з масивів
                System.out.println("Sum from ComplexNumber[]\t\t" + ComplexNumber.addNumbers(complexArray));
                System.out.println("Sum from List<ComplexNumber>\t\t" + ComplexNumber.addNumbers(complexList));
                System.out.println("Sum from Set<ComplexNumber>\t\t" + ComplexNumber.addNumbers(complexSet) + "\n");

                // Множення з масивів
                System.out.println("Multiply from ComplexNumber[]\t\t" + ComplexNumber.multiplyNumbers(complexArray));
                System.out.println(
                                "Multiply from List<ComplexNumber>\t\t" + ComplexNumber.multiplyNumbers(complexList));
                System.out.println("Multiply from Set<ComplexNumber>\t" + ComplexNumber.multiplyNumbers(complexSet)
                                + "\n\n");

                // =========================================================

                System.out.println("===FRACTIONS===");

                int n_fract = setArraySize();
                System.out.println();

                Fraction[] fractionsArray = new Fraction[n_fract];
                ArrayList<Fraction> fractionsList = new ArrayList<Fraction>();

                for (int i = 0; i < n_fract; i++) {
                        System.out.println(i + 1 + " element...");

                        Fraction tmp = setFraction();
                        fractionsArray[i] = tmp;
                        fractionsList.add(tmp);

                        System.out.println("Added " + tmp + "\n");
                }

                // Виведення масивів
                System.out.println("Fraction[]\t\t" + Arrays.toString(fractionsArray));
                System.out.println("List<Fraction>\t\t" + fractionsList);

                // Зміна масивів (за умовою)
                Fraction.sumFractionArray(fractionsArray);
                Fraction.sumFractionArray(fractionsList);

                // Виведення змінених масивів
                System.out.println("Changed Fraction[]\t\t" + Arrays.toString(fractionsArray));
                System.out.println("Changed List<Fraction>\t\t" + fractionsList);
        }
}
