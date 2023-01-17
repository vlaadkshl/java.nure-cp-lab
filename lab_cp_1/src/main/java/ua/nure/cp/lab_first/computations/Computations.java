package ua.nure.cp.lab_first.computations;

import java.util.ArrayList;
import java.util.Collection;

public class Computations {
    private ArrayList<Integer> numbers = new ArrayList<Integer>();

    public Computations(int[] numbers) {
        for (int num : numbers)
            if (num > 10) // Додаю лише числа, що складаються з більш ніж однієї цифри
                this.numbers.add(num);
    }

    public Computations(Collection<Integer> numbers) {
        this.numbers = new ArrayList<Integer>(numbers);
    }

    private boolean isPalindrome(int number) {
        int begin = number, end;

        /*
         * 1. Number = 253, End = 0
         * 2. Number = 25, End = 3
         * 3. Number = 2, End = 35
         * 4. Number = 0, End = 352
         * 
         * Number != End
         */

        for (end = 0; number != 0; number *= 0.1)
            end = end * 10 + number % 10;

        return begin == end;
    }

    public ArrayList<Integer> palindromesFromArray() {
        ArrayList<Integer> palindromes = new ArrayList<Integer>();

        for (int i = 0, numOfPalindromes = 0; i < numbers.size() && numOfPalindromes != 2; i++) {
            int elem = numbers.get(i);

            if (isPalindrome(elem)) {
                palindromes.add(elem);
                numOfPalindromes++;
            }
        }

        return palindromes;
    }

    public String returnPalindromes() {
        ArrayList<Integer> palindromes = palindromesFromArray();

        return switch (palindromes.size()) {
            case 0 -> "No palindromes found.";
            case 1 -> palindromes.get(0).toString();
            default -> palindromes.toString();
        };
    }
}