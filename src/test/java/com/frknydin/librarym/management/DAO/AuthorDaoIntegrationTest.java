package com.frknydin.librarym.management.DAO;


import com.frknydin.librarym.management.DAO.author.AuthorDao;
import com.frknydin.librarym.management.DAO.author.AuthorDaoImpl;
import com.frknydin.librarym.management.model.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {
    @Autowired
    AuthorDao authorDao;

    @Test
    void testGetAuthorByNameNative() {
        Author author = authorDao.findAuthorByNameNative("Craig","Walls");
        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthorByNameCriteria() {
        Author author = authorDao.findAuthorByNameCriteria("Craig","Walls");
        assertThat(author).isNotNull();
    }

    @Test
    void testAuthorFindAll () {
        List<Author> authors = authorDao.findAll();

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

    @Test
    void testListAuthorsByLastNameLike(){
        List<Author> authors = authorDao.listAuthorByLastNameList("Wall");

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("furkan");
        author.setLastName("aydin");

        Author saved = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthorById(saved.getId());


        Author deleted = authorDao.getById(saved.getId());
        assertThat(deleted).isNull();

    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("furkan");
        author.setLastName("aydin");

        Author saved = authorDao.saveNewAuthor(author);

        saved.setLastName("Aydin");
        Author updated = authorDao.updateAuthor(saved);

        assertThat(updated.getLastName()).isEqualTo("Aydin");
    }

    @Test
    void testSaveAuthor() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Thompson");
        Author saved = authorDao.saveNewAuthor(author);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void testGetAuthorByName() {
        Author author = authorDao.findAuthorByName("Craig", "Walls");

        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthor() {

        Author author = authorDao.getById(1L);

        assertThat(author).isNotNull();

    }
}
