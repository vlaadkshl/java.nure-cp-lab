package ua.nure.cp.book;

import java.util.concurrent.ConcurrentHashMap;

public class Books extends ConcurrentHashMap<Integer, Book> {
    @Override
    public String toString() {
        return keySet().toString();
    }
}
