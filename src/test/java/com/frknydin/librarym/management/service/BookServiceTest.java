package com.frknydin.librarym.management.service;

import com.frknydin.librarym.management.model.entity.Author;
import com.frknydin.librarym.management.model.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by jt on 8/28/21.
 */
@DataJpaTest
@Import({BookService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setIsbn("978-1245325574");
        book.setPublisher("Pearson");
        book.setTitle("Data Transaction");
        Book saved = bookService.saveNewBook(book);

        bookService.deleteBookById(saved.getId());

        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            Book deleted = bookService.getById(saved.getId());
        });
    }

    @Test
    void updateBookTest() {
        Book book = new Book();
        book.setIsbn("902-854225471");
        book.setPublisher("Silver");
        book.setTitle("Operating Systems");

        book.setAuthorId(2L);
        Book saved = bookService.saveNewBook(book);

        saved.setTitle("Modern Operating System");
        bookService.updateBook(saved);

        Book fetched = bookService.getById(saved.getId());

        assertThat(fetched.getTitle()).isEqualTo("Modern Operating System");
    }

    @Test
    void testSaveBook() {
        Book book = new Book();
        book.setIsbn("918-2521378746");
        book.setPublisher("Gold");
        book.setTitle("Computer Architecture");

        book.setAuthorId(1L);
        Book saved = bookService.saveNewBook(book);

        assertThat(saved).isNotNull();
    }

    @Test
    void testGetBookByName() {
        Book book = bookService.findBookByTitle("Clean Code");

        assertThat(book).isNotNull();
    }

    @Test
    void testGetBook() {
        Book book = bookService.getById(3L);

        assertThat(book.getId()).isNotNull();
    }

}
