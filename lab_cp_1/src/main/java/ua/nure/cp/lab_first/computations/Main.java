package ua.nure.cp.lab_first.computations;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
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
                        System.out.println("n is undefined: Cannot find n in string " + n_ + ". Try again...");
                    }
                }
            }

            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {
                int num = 0;
                while (num <= 10) {
                    System.out.print((i + 1) + " number: ");
                    String num_ = sc.nextLine();

                    if (num_.isBlank()) {
                        System.out.println("n is undefined: Cannot find n in blank string. Try again...");
                    } else {
                        try {
                            num = Integer.parseInt(num_);

                            if (num <= 10)
                                System.out.println("Number must be > 10. Try again...");
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Number is undefined: Cannot find a number in string " + num_ + ". Try again...");
                        }
                    }
                }

                numbers[i] = num;
            }

            Computations computations = new Computations(numbers);
            System.out.println(computations.returnPalindromes());
        }
    }
}
