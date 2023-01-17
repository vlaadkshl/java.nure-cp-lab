package ua.nure.cp.lab_first.strings;

import java.text.StringCharacterIterator;
import java.util.*;

public class LongestPalindrome {
    private String substring;

    HashMap<Character, Integer> presentChars = new HashMap<Character, Integer>();

    public LongestPalindrome(String str, int begin, int end) throws Exception {
        if (str.isBlank())
            throw new Exception("Input string is blank");

        int length = str.length();

        if (begin < 0)
            throw new ArithmeticException("Begin < 0");
        if (begin >= length)
            throw new ArithmeticException("Begin > length of \"" + str + "\"");

        if (end < begin)
            throw new ArithmeticException("Begin > End");
        if (end < 0)
            throw new ArithmeticException("End < 0");
        if (end >= length)
            throw new ArithmeticException("End > length of \"" + str + "\"");

        this.substring = str.substring(begin, end + 1);
    }

    private void setPresentChars() {
        for (StringCharacterIterator iter = new StringCharacterIterator(substring); iter
                .current() != StringCharacterIterator.DONE; iter.next()) {
            presentChars.compute(iter.current(), (k, v) -> (v == null ? 1 : ++v));
        }
    }

    private boolean isEvenPresent() {
        for (Map.Entry<Character, Integer> entry : presentChars.entrySet())
            if (entry.getValue() % 2 == 0 || entry.getValue() > 2)
                return true;

        return false;
    }

    TreeSet<String> getPermutationList(String str) {
        /*
         * ВХІД: abc
         * 1. Доходить до пустого рядку
         * 2. Йде наверх із поступовим збільшенням рядку: "", "c", "bc", "abc"
         * 
         * word.substring(0,i) - вертає рядок від 0 включно до i невключно
         * word.substring(i) - вертає рядок від i символу включно
         */
        TreeSet<String> perm = new TreeSet<String>();

        if (str.isBlank() || str.length() == 0) {
            perm.add("");
            return perm;
        }

        // Рекурсивний виклик методу із переданим обрізаним на один символ рядком
        TreeSet<String> words = getPermutationList(str.substring(1));
        Character firstCharacter = str.charAt(0);

        for (String word : words) {
            for (int i = 0; i <= word.length(); i++) {
                String toAdd = word.substring(0, i) + firstCharacter + word.substring(i);
                perm.add(toAdd);
            }
        }

        return perm;
    }

    TreeSet<String> findPalindromes() {
        this.setPresentChars();

        if (isEvenPresent()) {
            StringBuilder even = new StringBuilder();
            TreeSet<Character> odd = new TreeSet<>();

            for (Map.Entry<Character, Integer> entry : presentChars.entrySet()) {
                char ch = entry.getKey();
                int freq = entry.getValue();

                if (freq % 2 == 0) {
                    even.append(String.valueOf(ch).repeat(freq >> 1));
                } else {
                    odd.add(ch);

                    if (freq > 2)
                        even.append(String.valueOf(ch).repeat(--freq >> 1));
                }
            }

            TreeSet<String> result = new TreeSet<String>();

            for (String evenString : getPermutationList(even.toString())) {
                StringBuilder evenBuilder = new StringBuilder(evenString);

                if (odd.isEmpty()) {
                    result.add(evenBuilder.toString() + evenBuilder.reverse());
                } else {
                    for (Character ch : odd)
                        result.add(evenBuilder.toString() + ch + evenBuilder.reverse());
                }
            }

            return result;
        } else
            return new TreeSet<String>();
    }

    public String returnPalindromes() {
        TreeSet<String> palindromes = findPalindromes();

        return !palindromes.isEmpty() ? palindromes.toString() : "Error";
    }
}