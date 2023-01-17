package ua.nure.cp;

import ua.nure.cp.enterable.Library;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.Collectors;

public final class Utils {
    public static final SecureRandom random = new SecureRandom();

    private Utils() {
        throw new IllegalStateException("Utils can't be created. It's utility class!");
    }

    private static synchronized TreeSet<Integer> getWantedSet(Reader reader, Library library) {
        if (reader.getWantedList().isEmpty()) {
            var allBooks = new ArrayList<>(library.allBooks.keySet());

            var wantedList = new TreeSet<Integer>();

            if (allBooks.size() > 1) {
                for (int i = 0; !allBooks.isEmpty() && i < random.nextInt(1, library.getMaxGivenBooksNumber()); i++) {
                    int randomElementIndex = (allBooks.size() > 1)
                            ? random.nextInt(0, allBooks.size() - 1)
                            : 0;

                    wantedList.add(allBooks.get(randomElementIndex));
                    allBooks.remove(randomElementIndex);
                }
            } else wantedList.add(allBooks.get(0));

            return wantedList;
        } else {
            return reader.getWantedList();
        }
    }

    public static synchronized TreeSet<Integer> giveBooks(Reader reader, Library library) {
        TreeSet<Integer> givenIDSet = new TreeSet<>();

        TreeSet<Integer> wantedSet = getWantedSet(reader, library);
        Utils.printMessage(MESSAGE_TYPE_ENUM.WANTED_LIST, reader, library, wantedSet);

        TreeSet<Integer> availableFromWanted = wantedSet.stream()
                .filter(library.getAvailableBooks().keySet()::contains)
                .collect(Collectors.toCollection(TreeSet::new));

        if (!availableFromWanted.isEmpty()) {
            Utils.printMessage(MESSAGE_TYPE_ENUM.GET_AVAILABLE, reader, library, availableFromWanted);

            reader.addToReadingList(library.giveOutBooks(availableFromWanted));
            wantedSet.removeAll(availableFromWanted);

            givenIDSet.addAll(availableFromWanted);

            library.removeFromCommonWantedSet(availableFromWanted);
        }

        if (!wantedSet.isEmpty()) {
            Utils.printMessage(MESSAGE_TYPE_ENUM.NEEDS_TO_READ, reader, library, wantedSet);

            reader.addToWantedList(wantedSet);
            library.addToCommonWantedSet(wantedSet);
        }

        return givenIDSet;
    }

    public static void returnBooks(Reader reader, Library library) {
        synchronized (library) {
            library.returnBooks(reader.getReadingList());
            library.notifyAll();
            Utils.printMessage(MESSAGE_TYPE_ENUM.RETURN_BOOKS, reader, library);
        }
        synchronized (reader) {
            reader.returnBooks();
        }
    }

    public static void printMessage(MESSAGE_TYPE_ENUM message, Reader reader, Library library) {
        System.out.println(switch (message) {
            case ENTER, LEAVE -> "" + reader + message + library.getVisitorsMessage();

            case READ_BOOKS -> "" + reader + message + reader.getReadingList();
            case RETURN_BOOKS ->
                    "" + reader + message + reader.getReadingList() + ". " + library.getAvailableBooksSizeMessage();

            default -> "";
        });
    }

    public static void printMessage(MESSAGE_TYPE_ENUM message, Reader reader, Library library, TreeSet<Integer> books) {
        System.out.println(switch (message) {
            case TAKE_BOOKS -> {
                String takenMessage = !books.isEmpty() ? message + books.toString() + ". " : " hasn't taken any books. ";
                yield "" + reader + takenMessage + library.getAvailableBooksSizeMessage();
            }
            case READ_READING_ROOM, READ_HOME, WANTED_LIST, GET_AVAILABLE, NEEDS_TO_READ ->
                    "" + reader + message + books;

            default -> "";
        });
    }

    public enum MESSAGE_TYPE_ENUM {
        // Reader
        TAKE_BOOKS(" has taken books: "),
        READ_BOOKS(" is reading this books: "),
        READ_READING_ROOM(" is reading these books in reading room: "),
        READ_HOME(" is reading these books at home: "),
        RETURN_BOOKS(" returned these books: "),

        // Getting books
        WANTED_LIST(" wanted list: "),
        GET_AVAILABLE(" has got these available books: "),
        NEEDS_TO_READ(" needs to read: "),

        // Library
        ENTER(" entered the library. "),
        LEAVE(" left the library. ");

        private final String message;

        MESSAGE_TYPE_ENUM(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return this.message;
        }
    }
}
