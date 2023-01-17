package ua.nure.cp;

import ua.nure.cp.book.Book;
import ua.nure.cp.book.Books;
import ua.nure.cp.enterable.Library;
import ua.nure.cp.enterable.ReadingRoom;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        final int readersNumber = setNumber("number of readers", 10);

        final int numBooks = setNumber("number of books", 50);
        Books books = new Books();
        for (int i = 0; i < numBooks; i++) {
            Book book = new Book(Utils.random.nextBoolean());
            books.put(book.id(), book);
        }

        System.out.println("LIBRARY");
        Library library = new Library(books, setNumber("max visitors number", 10),
                setNumber("max given books number", 10));

        System.out.println("READING ROOM:");
        ReadingRoom readingRoom = new ReadingRoom(setNumber("max visitors number", 20));

        final long time = setNumber("duration of thread working (in milliseconds)", 10000);

        System.out.println("\n" + library.getAvailableBooksSizeMessage().toUpperCase() + "\n");

        ExecutorService executors = Executors.newFixedThreadPool(readersNumber);
        for (int i = 0; i < readersNumber; i++) {
            executors.submit(new Reader(library, readingRoom, time));
        }
        executors.shutdown();
    }

    private static int setNumber(String msg, int defaultValue) {
        int n = 0;

        while (n <= 0) {
            System.out
                    .print("Enter " + msg + " (DEFAULT VALUE = " + defaultValue + ", press ENTER to set this value): ");
            String nString = sc.nextLine();

            if (nString.isBlank()) {
                return defaultValue;
            } else {
                try {
                    n = Integer.parseInt(nString);

                    if (n <= 0)
                        System.err.println("n must be > 0. Try again...");
                } catch (NumberFormatException e) {
                    System.err.println("n is undefined: Cannot find n in string " + nString + ". Try again...");
                }
            }
        }

        return n;
    }
}