package ua.nure.cp;

import ua.nure.cp.book.Book;
import ua.nure.cp.book.Books;
import ua.nure.cp.enterable.Library;
import ua.nure.cp.enterable.ReadingRoom;

import java.util.TreeSet;
import java.util.stream.Collectors;

public class Reader implements Runnable {
    private static int id = 1;
    private final long END_TIME;

    private final int readerID;
    private final Library library;
    private final ReadingRoom readingRoom;
    private final Books readingList = new Books();
    private final TreeSet<Integer> wantedList = new TreeSet<>();

    public Reader(Library library, ReadingRoom readingRoom, long endTime) {
        this.readerID = id++;
        this.library = library;
        this.readingRoom = readingRoom;
        this.END_TIME = endTime;
    }

    public Books getReadingList() {
        return readingList;
    }

    public synchronized void addToReadingList(Books books) {
        readingList.putAll(books);
        wantedList.removeAll(books.keySet());
    }

    public TreeSet<Integer> getWantedList() {
        return wantedList;
    }

    public void addToWantedList(TreeSet<Integer> wantedList) {
        this.wantedList.addAll(wantedList);
    }

    private void enterLibrary() {
        synchronized (library) {
            library.enter();
            Utils.printMessage(Utils.MESSAGE_TYPE_ENUM.ENTER, this, library);
        }
    }

    private void leaveLibrary() {
        synchronized (library) {
            library.leave();
            Utils.printMessage(Utils.MESSAGE_TYPE_ENUM.LEAVE, this, library);
        }
    }

    private void takeBooks() throws InterruptedException {
        while (library.getAvailableBooks().isEmpty()) library.wait();

        TreeSet<Integer> takenBooks = Utils.giveBooks(this, library);
        Utils.printMessage(Utils.MESSAGE_TYPE_ENUM.TAKE_BOOKS, this, library, takenBooks);
    }

    private void readBooks() throws InterruptedException {
        if (!readingList.isEmpty()) {
            Utils.printMessage(Utils.MESSAGE_TYPE_ENUM.READ_BOOKS, this, library);

            // Читання в читальній залі
            TreeSet<Integer> booksReadingRoom = readingList.values().parallelStream().filter(Book::onlyReadingRoom).map(Book::id).collect(Collectors.toCollection(TreeSet::new));
            if (!booksReadingRoom.isEmpty()) {
                readingRoom.enter();

                Utils.printMessage(Utils.MESSAGE_TYPE_ENUM.READ_READING_ROOM, this, library, booksReadingRoom);
                Thread.sleep(500);

                readingRoom.leave();
            }

            // Читання вдома
            TreeSet<Integer> booksHome = readingList.values().parallelStream().filter(b -> !b.onlyReadingRoom()).map(Book::id).collect(Collectors.toCollection(TreeSet::new));
            if (!booksHome.isEmpty()) {
                Utils.printMessage(Utils.MESSAGE_TYPE_ENUM.READ_HOME, this, library, booksHome);
                Thread.sleep(500);
            }
        }
    }

    public synchronized void returnBooks() {
        readingList.clear();
    }

    private void finalReturnBooks() {
        if (!readingList.isEmpty()) synchronized (library) {
            enterLibrary();
            Utils.returnBooks(this, library);
            leaveLibrary();
        }
    }

    @Override
    public void run() {
        try {
            final long END_OF_WORK = System.currentTimeMillis() + END_TIME;

            while (System.currentTimeMillis() <= END_OF_WORK) {
                // Якщо читач захотів піти в бібліотеку
                if (Utils.random.nextBoolean()) {
                    enterLibrary();

                    // ЗА БАЖАННЯМ читач здає книги, якщо вони у нього присутні
                    if (Utils.random.nextBoolean() && !readingList.isEmpty())
                        Utils.returnBooks(this, library);

                    takeBooks();
                    leaveLibrary();
                    readBooks();

                    Thread.sleep(500);
                }
            }
            finalReturnBooks();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "Reader " + readerID;
    }
}