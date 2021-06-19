package ru.mpetrov.booksapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mpetrov.booksapi.domain.Book;
import ru.mpetrov.booksapi.repository.BookRepository;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class BookController {
    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public ResponseEntity<Iterable<Book>> getBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @RequestMapping(value = "/books", method = {RequestMethod.
            POST, RequestMethod.PUT})
    public ResponseEntity<?> createBook(@Valid @RequestBody Book book,
                                        Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().
                    body(errors);
        }
        Book result = bookRepository.save(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
