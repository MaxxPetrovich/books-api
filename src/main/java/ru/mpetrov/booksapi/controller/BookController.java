package ru.mpetrov.booksapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mpetrov.booksapi.domain.Book;
import ru.mpetrov.booksapi.exceptions.BookNotFoundException;
import ru.mpetrov.booksapi.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public ResponseEntity<Iterable<Book>> getAllBooks(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String author) {
        List<Book> list = bookRepository.findByNameContainingAndAuthorContaining(name, author);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/books")
    Book newBook(@RequestBody Book newEmployee) {
        return bookRepository.save(newEmployee);
    }

    // Single item

    @GetMapping("/books/{id}")
    Book one(@PathVariable Long id) {

        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PutMapping("/books/{id}")
    Book replaceBook(@RequestBody Book newEmployee, @PathVariable Long id) {

        return bookRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setAuthor(newEmployee.getAuthor());
                    return bookRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return bookRepository.save(newEmployee);
                });
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
