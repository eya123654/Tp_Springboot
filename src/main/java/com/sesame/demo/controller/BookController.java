package com.sesame.demo.controller;

import com.sesame.demo.domain.Book;
import com.sesame.demo.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Livres", description = "Gestion des livres de la bibliothèque")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    @Operation(summary = "Get all books", description = "Retrieve a list of all books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/search")
    @Operation(summary = "Search books", description = "Search for books by title, author, or category")
    public List<Book> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category) {
        return bookService.search(title, author, category);
    }

    @GetMapping("/books/{id}")
    @Operation(summary = "Get book by ID", description = "Retrieve a single book by its ID")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/books")
    @Operation(summary = "Créer un livre")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book created = bookService.create(book);
        URI location = URI.create("/api/books/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/books/{id}")
    @Operation(summary = "Modifier un livre")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        try {
            Book updated = bookService.update(id, book);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer un livre")
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/books/count")
    @Operation(summary = "Count all books", description = "Get the total number of books")
    public long countBooks() {
        return bookService.count();
    }
}
