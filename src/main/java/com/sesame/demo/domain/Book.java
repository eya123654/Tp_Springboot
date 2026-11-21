package com.sesame.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    @Size(max = 200, message = "Le titre ne doit pas dépasser 200 caractères")
    @Column(nullable = false, length = 200)
    private String title;

    @NotBlank(message = "L'auteur est obligatoire")
    @Size(max = 255, message = "L'auteur ne doit pas dépasser 255 caractères")
    @Column(nullable = false)
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private Author authorEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_categories", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonIgnore
    private Set<Category> categories = new HashSet<>();

    @NotBlank(message = "L'ISBN est obligatoire")
    @Size(max = 255, message = "L'ISBN ne doit pas dépasser 255 caractères")
    @Column(nullable = false, unique = true)
    private String isbn;

    @NotNull(message = "La disponibilité est obligatoire")
    @Column(nullable = false)
    private Boolean available = true;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        if (author != null && !author.isBlank()) {
            return author;
        }
        return authorEntity != null ? authorEntity.getName() : null;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Author getAuthorEntity() {
        return authorEntity;
    }

    public void setAuthorEntity(Author authorEntity) {
        this.authorEntity = authorEntity;
        if (authorEntity != null && (author == null || author.isBlank())) {
            this.author = authorEntity.getName();
        }
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories == null ? new HashSet<>() : categories;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
