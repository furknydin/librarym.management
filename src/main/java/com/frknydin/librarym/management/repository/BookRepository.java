package com.frknydin.librarym.management.repository;

import com.frknydin.librarym.management.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByTitle (String title);
}
