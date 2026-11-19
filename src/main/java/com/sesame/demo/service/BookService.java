package com.sesame.demo.service;

import com.sesame.demo.domain.Book;
import com.sesame.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> search(String title, String author, String category) {
        String cleanTitle = normalize(title);
        String cleanAuthor = normalize(author);
        String cleanCategory = normalize(category);

        if (cleanTitle.isEmpty() && cleanAuthor.isEmpty() && cleanCategory.isEmpty()) {
            return bookRepository.findAll();
        }

        return bookRepository.searchBooks(cleanTitle, cleanAuthor, cleanCategory);
    }

    public Book create(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Le livre ne peut pas être nul.");
        }
        book.setId(null);
        return bookRepository.save(book);
    }

    public Book update(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livre introuvable : " + id));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setAvailable(bookDetails.getAvailable());

        return bookRepository.save(book);
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Livre introuvable : " + id);
        }
        bookRepository.deleteById(id);
    }

    public long count() {
        return bookRepository.count();
    }

    private String normalize(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }
}
