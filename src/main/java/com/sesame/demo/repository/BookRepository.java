package com.sesame.demo.repository;

import com.sesame.demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b left join b.authorEntity a left join b.categories c " +
            "where (:title is null or :title = '' or lower(b.title) like lower(concat('%', :title, '%'))) " +
            "and (:author is null or :author = '' or lower(coalesce(b.author, a.name)) like lower(concat('%', :author, '%'))) "
            +
            "and (:category is null or :category = '' or lower(c.name) like lower(concat('%', :category, '%'))) " +
            "group by b")
    List<Book> searchBooks(@Param("title") String title,
            @Param("author") String author,
            @Param("category") String category);
}
