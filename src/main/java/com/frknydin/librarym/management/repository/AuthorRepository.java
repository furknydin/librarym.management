package com.frknydin.librarym.management.repository;

import com.frknydin.librarym.management.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}