package ru.mpetrov.booksapi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mpetrov.booksapi.domain.Book;

public interface BookRepository extends
        CrudRepository<Book, Long> {
}
