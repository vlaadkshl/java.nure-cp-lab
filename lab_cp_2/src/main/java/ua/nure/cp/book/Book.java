package ua.nure.cp.book;

public record Book(int id, boolean onlyReadingRoom) {

    private static int id_ = 1;

    public Book(boolean onlyReadingRoom) {
        this(id_, onlyReadingRoom);
        ++id_;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}