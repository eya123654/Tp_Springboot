package com.sesame.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.sesame.demo.domain.Book;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

class BookValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void livreValideNeProduitAucuneErreur() {
        Book book = new Book();
        book.setTitle("Candide");
        book.setAuthor("Voltaire");
        book.setIsbn("978-2-07-061276-5");
        book.setAvailable(true);

        assertTrue(validator.validate(book).isEmpty());
    }

    @Test
    void titreVideEstRefuse() {
        Book book = new Book();
        book.setTitle("");
        book.setAuthor("Voltaire");
        book.setIsbn("978-2-07-061276-5");
        book.setAvailable(true);

        Set<ConstraintViolation<Book>> erreurs = validator.validate(book);

        assertEquals(1, erreurs.stream()
                .filter(erreur -> erreur.getPropertyPath().toString().equals("title"))
                .count());
    }

    @Test
    void champsObligatoiresSontRefuses() {
        Book book = new Book();
        book.setTitle("");
        book.setAuthor("");
        book.setIsbn("");
        book.setAvailable(null);

        Set<ConstraintViolation<Book>> erreurs = validator.validate(book);

        assertEquals(4, erreurs.size());
    }
}
