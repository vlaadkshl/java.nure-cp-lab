package ua.nure.cp.enterable;

import ua.nure.cp.book.Book;
import ua.nure.cp.book.Books;

import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Library extends Enterable {
    public final Books allBooks;
    public final int MAX_GIVEN_BOOKS;
    private final Books availableBooks;
    private final TreeSet<Integer> commonWantedSet = new TreeSet<>();

    public Library(Books books, int maxVisitors, int maxGivenBooks) {
        super(maxVisitors, "library");
        MAX_GIVEN_BOOKS = maxGivenBooks;
        this.availableBooks = books;
        this.allBooks = books.values().stream().collect(Collectors.toConcurrentMap(
                Book::id,
                Function.identity(),
                (b1, b2) -> b1,
                Books::new)
        );
    }

    public final int getMaxGivenBooksNumber() {
        return MAX_GIVEN_BOOKS;
    }

    public final Books getAvailableBooks() {
        return availableBooks;
    }

    public final String getAvailableBooksSizeMessage() {
        return "Library has " + availableBooks.size() + " books.";
    }

    public final synchronized Books giveOutBooks(TreeSet<Integer> booksID) {
        return booksID.parallelStream()
                .map(availableBooks::remove)
                .collect(Collectors.toConcurrentMap(Book::id, Function.identity(), (b1, b2) -> b1, Books::new));
    }

    public final synchronized void returnBooks(Books books) {
        availableBooks.putAll(books);
    }

    public final synchronized void addToCommonWantedSet(TreeSet<Integer> wantedSet) {
        commonWantedSet.addAll(wantedSet);
    }

    public final synchronized void removeFromCommonWantedSet(TreeSet<Integer> set) {
        commonWantedSet.removeAll(set);
    }
}