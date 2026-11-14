package com.sesame.demo.service;

import com.sesame.demo.domain.Author;
import com.sesame.demo.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public Optional<Author> findByName(String name) {
        return authorRepository.findByNameIgnoreCase(name);
    }

    public Author create(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("L'auteur ne peut pas être nul.");
        }
        return authorRepository.save(author);
    }
}
