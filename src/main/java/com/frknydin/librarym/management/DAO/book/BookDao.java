package com.frknydin.librarym.management.DAO.book;

import com.frknydin.librarym.management.model.entity.Book;

public interface BookDao {
    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);
}
