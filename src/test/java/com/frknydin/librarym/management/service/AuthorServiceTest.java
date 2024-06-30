package com.frknydin.librarym.management.service;

import com.frknydin.librarym.management.model.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import({AuthorService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorServiceTest {
    @Autowired
    AuthorService authorService;

    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("Mario");
        author.setLastName("Gomez");

        Author saved = authorService.saveNewAuthor(author);

        authorService.deleteAuthorById(saved.getId());

        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            Author deleted = authorService.getById(saved.getId());
        });

    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("Ozan");
        author.setLastName("Can");

        Author saved = authorService.saveNewAuthor(author);

        saved.setLastName("Balkışlı");
        Author updated = authorService.updateAuthor(saved);

        assertThat(updated.getLastName()).isEqualTo("Balkışlı");
    }

    @Test
    void testSaveAuthor() {
        Author author = new Author();
        author.setFirstName("İslam");
        author.setLastName("Harun");
        Author saved = authorService.saveNewAuthor(author);

        assertThat(saved).isNotNull();
    }

    @Test
    void testGetAuthorByName() {
        Author author = authorService.findAuthorByName("Craig", "Walls");

        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthor() {

        Author author = authorService.getById(1L);

        assertThat(author).isNotNull();

    }
}
