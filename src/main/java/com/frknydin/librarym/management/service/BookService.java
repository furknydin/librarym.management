package com.frknydin.librarym.management.service;

import com.frknydin.librarym.management.DAO.book.BookDao;
import com.frknydin.librarym.management.model.entity.Book;
import com.frknydin.librarym.management.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class BookService implements BookDao {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findBookByTitleNative(String title) {
        return null;
    }

    @Override
    public Book findBookByTitleCriteria(String title) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return List.of();
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return null;
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getReferenceById(id);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public Book saveNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        Optional<Book> foundBook = bookRepository.findById(book.getId());

        if (foundBook.isPresent()) {
            Book temp = foundBook.get();
            temp.setTitle(book.getTitle());
            temp.setAuthorId(book.getAuthorId());
            temp.setIsbn(book.getIsbn());
            temp.setPublisher(book.getPublisher());

            return bookRepository.save(temp);
        }

        return null;
    }

    @Transactional
    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
