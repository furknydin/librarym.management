package com.frknydin.librarym.management.DAO.author;

import com.frknydin.librarym.management.model.entity.Author;

import java.util.List;

public interface AuthorDao {

    Author findAuthorByNameNative (String firstName, String lastName);

    Author findAuthorByNameCriteria (String firstName, String lastName);

    List<Author> findAll();

    List<Author> listAuthorByLastNameList(String lastName);

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}