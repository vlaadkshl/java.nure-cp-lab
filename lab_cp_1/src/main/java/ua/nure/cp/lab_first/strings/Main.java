package ua.nure.cp.lab_first.strings;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    private static int setIntNumber(String msg) {
        int n = Integer.MIN_VALUE;

        while (n < 0) {
            System.out.print("Enter " + msg + ": ");
            String n_ = sc.nextLine();

            if (n_.isBlank()) {
                System.out.println("n is undefined: Cannot find n in blank string. Try again...");
            } else {
                try {
                    if ((n = Integer.parseInt(n_)) < 0)
                        System.out.println("n must be >= 0. Try again...");
                } catch (NumberFormatException e) {
                    System.out.println("n is undefined: Cannot find n in string " + n_ + ". Try again...");
                }
            }
        }

        return n;
    }

    public static void main(String[] args) {
        System.out.print("Enter string: ");
        String string = sc.nextLine();

        while (true) {
            System.out.println("Your string is: \"" + string + "\"");
            try {
                LongestPalindrome pal = new LongestPalindrome(string, setIntNumber("begin"), setIntNumber("end"));
                System.out.println(pal.returnPalindromes());
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\nTry again...\n");
            }
        }
    }
}
