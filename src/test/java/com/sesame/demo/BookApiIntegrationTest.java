package com.sesame.demo;

import com.sesame.demo.domain.Book;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    @WithMockUser(username = "user", roles = "USER")
    void shouldListBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    @Order(2)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldCreateBook() throws Exception {
        Book book = new Book();
        book.setTitle("Le Petit Prince");
        book.setAuthor("Antoine de Saint-Exupéry");
        book.setIsbn("978-2-07-040851-8");
        book.setAvailable(true);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Le Petit Prince"))
                .andExpect(jsonPath("$.author").value("Antoine de Saint-Exupéry"));
    }

    @Test
    @Order(3)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldUpdateAndDeleteBook() throws Exception {
        Book book = new Book();
        book.setTitle("Candide");
        book.setAuthor("Voltaire");
        book.setIsbn("978-2-07-061276-5");
        book.setAvailable(true);

        String response = mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Book created = objectMapper.readValue(response, Book.class);

        book.setTitle("Candide modifié");
        mockMvc.perform(put("/api/books/{id}", created.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Candide modifié"));

        mockMvc.perform(delete("/api/books/{id}", created.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/api/books/{id}", created.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(4)
    @WithMockUser(username = "user", roles = "USER")
    void shouldRejectBookWithoutTitle() throws Exception {
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"author\":\"Anonyme\",\"isbn\":\"978-X\",\"available\":true}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(5)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldDeleteBook() throws Exception {
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(6)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldNotDeleteBook_NotFound() throws Exception {
        mockMvc.perform(delete("/api/books/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(7)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldNotCreateBook_InvalidTitle() throws Exception {
        Book book = new Book();
        book.setTitle("");
        book.setAuthor("Antoine de Saint-Exupéry");
        book.setIsbn("978-2-07-040851-8");
        book.setAvailable(true);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest());
    }
}
