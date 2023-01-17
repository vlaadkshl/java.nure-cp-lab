package ua.nure.cp.lab_first.strings;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

public class LongestPalindromeTest {
    @Test
    public void permutationTest() {
        try {
            LongestPalindrome pal1 = new LongestPalindrome("cheese", 1, 4);
            assertEquals(new HashSet<>(Arrays.asList("ehe", "ese")), pal1.findPalindromes()); // hees

            LongestPalindrome pal2 = new LongestPalindrome("adababad", 0, 5);
            assertEquals(new HashSet<>(Arrays.asList("abdba", "badab")), pal2.findPalindromes()); // adabab

            LongestPalindrome pal3 = new LongestPalindrome("weekend", 3, 6);
            assertEquals(new HashSet<>(), pal3.findPalindromes());
        } catch (Exception e) {

        }
    }
}