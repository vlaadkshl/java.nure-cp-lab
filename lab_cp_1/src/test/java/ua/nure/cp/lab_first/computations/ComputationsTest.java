package ua.nure.cp.lab_first.computations;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import org.junit.Test;

public class ComputationsTest {
        @Test
        public void noPalindromTest() {
                Computations computations = new Computations(new int[] { 412, 584, 11144789, 78530 });

                ArrayList<Integer> palindroms = computations.palindromesFromArray();

                int[] res = new int[palindroms.size()];

                for (int i = 0; i < palindroms.size(); i++) {
                        res[i] = palindroms.get(i);
                }

                assertArrayEquals(new int[] {}, res);
        }

        @Test
        public void onePalindromTest() {
                Computations computations = new Computations(new int[] { 412, 584, 1258521, 78530 });

                ArrayList<Integer> palindroms = computations.palindromesFromArray();

                int[] res = new int[palindroms.size()];

                for (int i = 0; i < palindroms.size(); i++) {
                        res[i] = palindroms.get(i);
                }

                assertArrayEquals(new int[] { 1258521 }, res);
        }

        @Test
        public void twoPalindromsTest() {
                Computations computations = new Computations(new int[] { 41214, 584, 1258521, 78530 });

                ArrayList<Integer> palindroms = computations.palindromesFromArray();

                int[] res = new int[palindroms.size()];

                for (int i = 0; i < palindroms.size(); i++) {
                        res[i] = palindroms.get(i);
                }

                assertArrayEquals(new int[] { 41214, 1258521 }, res);
        }

        @Test
        public void moreThanTwoPalindromsTest() {
                Computations computations = new Computations(new int[] { 41214, 584, 78588587, 1258521, 78530 });

                ArrayList<Integer> palindroms = computations.palindromesFromArray();

                int[] res = new int[palindroms.size()];

                for (int i = 0; i < palindroms.size(); i++) {
                        res[i] = palindroms.get(i);
                }

                assertArrayEquals(new int[] { 41214, 78588587 }, res);
        }
}
