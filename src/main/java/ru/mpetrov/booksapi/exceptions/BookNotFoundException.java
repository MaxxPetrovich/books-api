package ru.mpetrov.booksapi.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
