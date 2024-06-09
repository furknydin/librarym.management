package com.frknydin.librarym.management.DAO;

import com.frknydin.librarym.management.DAO.book.BookDao;
import com.frknydin.librarym.management.DAO.book.BookDaoImpl;
import com.frknydin.librarym.management.model.entity.Book;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import(BookDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoIntegrationTest {
    @Autowired
    BookDao bookDao;
    @Test
    void findBookByTitleNative() {
        Book book = bookDao.findBookByTitleNative("Clean Code");

        assertThat(book).isNotNull();
    }

    @Test
    void findBookByTitleCriteria() {
        Book book = bookDao.findBookByTitleCriteria("Clean Code");

        assertThat(book).isNotNull();
    }

    @Test
    void testFindAllBook() {
        List<Book> books = bookDao.findAll();

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    void testFindBookByISBN() {
        Book book = new Book();
        book.setIsbn("1234" + RandomString.make());
        book.setTitle("ISBN Test");

        Book saved = bookDao.saveNewBook(book);

        Book fetched = bookDao.findBookByIsbn(book.getIsbn());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        Book deleted = bookDao.getById(saved.getId());

        assertThat(deleted).isNull();
    }

    @Test
    void updateBookTest() {
        Book book = new Book();
        book.setIsbn("978-5174494368");
        book.setPublisher("Pearson");
        book.setTitle("Hibernate Trips");

        book.setAuthorId(2L);
        Book saved = bookDao.saveNewBook(book);

        saved.setTitle("Hibernate Beginner");
        bookDao.updateBook(saved);

        Book fetched = bookDao.getById(saved.getId());

        assertThat(fetched.getTitle()).isEqualTo("Hibernate Beginner");
    }

    @Test
    void testSaveBook() {
        Book book = new Book();
        book.setIsbn("978-2134494165");
        book.setPublisher("Pearson");
        book.setTitle("Concurrent Java");

        book.setAuthorId(1L);
        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
    }

    @Test
    void testGetBookByName() {
        Book book = bookDao.findBookByTitle("Clean Code");

        assertThat(book).isNotNull();
    }

    @Test
    void testGetBook() {
        Book book = bookDao.getById(3L);

        assertThat(book.getId()).isNotNull();
    }

}
