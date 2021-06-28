package ru.mpetrov.booksapi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mpetrov.booksapi.domain.Book;

import java.util.List;

public interface BookRepository extends
        CrudRepository<Book, Long> {
    List<Book> findByNameContainingAndAuthorContaining(String name, String author);
}
