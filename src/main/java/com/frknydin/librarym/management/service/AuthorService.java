package com.frknydin.librarym.management.service;

import com.frknydin.librarym.management.DAO.author.AuthorDao;
import com.frknydin.librarym.management.model.entity.Author;
import com.frknydin.librarym.management.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

public class AuthorService implements AuthorDao {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findAuthorByNameNative(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author findAuthorByNameCriteria(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<Author> findAll() {
        return List.of();
    }

    @Override
    public List<Author> listAuthorByLastNameList(String lastName) {
        return List.of();
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.getReferenceById(id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName,lastName).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public Author saveNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author updateAuthor(Author author) {
        Author foundAuthor = authorRepository.getReferenceById(author.getId());
        foundAuthor.setLastName(author.getLastName());
        foundAuthor.setFirstName(author.getFirstName());


        return authorRepository.save(foundAuthor);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
}
