package com.sesame.demo.data;

import com.sesame.demo.domain.Author;
import com.sesame.demo.domain.Book;
import com.sesame.demo.domain.Category;
import com.sesame.demo.repository.AuthorRepository;
import com.sesame.demo.repository.BookRepository;
import com.sesame.demo.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public DataInitializer(BookRepository bookRepository,
            AuthorRepository authorRepository,
            CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        if (bookRepository.count() == 0) {
            Author victorHugo = authorRepository.save(new Author("Victor Hugo", "Français"));
            Author stendhal = authorRepository.save(new Author("Stendhal", "Français"));
            Category roman = categoryRepository.save(new Category("Roman"));
            Category classique = categoryRepository.save(new Category("Classique"));

            Book book1 = new Book();
            book1.setTitle("Les Misérables");
            book1.setAuthor("Victor Hugo");
            book1.setAuthorEntity(victorHugo);
            book1.setCategories(Set.of(roman, classique));
            book1.setIsbn("978-2-01-000001-1");
            book1.setAvailable(true);

            Book book2 = new Book();
            book2.setTitle("Le Rouge et le Noir");
            book2.setAuthor("Stendhal");
            book2.setAuthorEntity(stendhal);
            book2.setCategories(Set.of(roman));
            book2.setIsbn("978-2-01-000002-8");
            book2.setAvailable(false);

            bookRepository.saveAll(List.of(book1, book2));
        }
    }
}
