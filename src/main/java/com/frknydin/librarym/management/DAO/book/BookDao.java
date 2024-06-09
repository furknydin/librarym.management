package com.frknydin.librarym.management.DAO.book;

import com.frknydin.librarym.management.model.entity.Book;

import java.util.List;

public interface BookDao {
    Book findBookByTitleNative(String title);

    Book findBookByTitleCriteria(String title);

    List<Book> findAll();

    Book findBookByIsbn (String isbn);

    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);
}
