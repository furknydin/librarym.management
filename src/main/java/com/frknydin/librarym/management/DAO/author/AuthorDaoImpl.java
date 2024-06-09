package com.frknydin.librarym.management.DAO.author;

import com.frknydin.librarym.management.model.entity.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory emf;

    public AuthorDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Author findAuthorByNameNative(String firstName, String lastName) {
        try (EntityManager em = getEntityManager()) {
            Query query = em.createNativeQuery("SELECT * FROM author a WHERE a.first_name = ? and a.last_name = ?", Author.class);
            query.setParameter(1,firstName);
            query.setParameter(2,lastName);

            return (Author) query.getSingleResult();
        }
    }

    @Override
    public Author findAuthorByNameCriteria(String firstName, String lastName) {
        try (EntityManager em = getEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);

            Root<Author> root = criteriaQuery.from(Author.class);
            ParameterExpression<String> firstNameParam = criteriaBuilder.parameter(String.class);
            ParameterExpression<String> lastNameParam = criteriaBuilder.parameter(String.class);

            Predicate firstNamePre = criteriaBuilder.equal(root.get("firstName"),firstNameParam);
            Predicate lastNamePre = criteriaBuilder.equal(root.get("lastName"),lastNameParam);

            criteriaQuery.select(root).where(criteriaBuilder.and(firstNamePre,lastNamePre));

            TypedQuery<Author> query = em.createQuery(criteriaQuery);
            query.setParameter(firstNameParam, firstName);
            query.setParameter(lastNameParam, lastName);

            return query.getSingleResult();

        }
    }

    @Override
    public List<Author> findAll() {
        EntityManager em = getEntityManager();

        try{
            TypedQuery<Author> query = em.createNamedQuery("author_find_all",Author.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }

    @Override
    public List<Author> listAuthorByLastNameList(String lastName) {
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Author> query = em.createQuery("SELECT a from Author a where a.lastName like :last_name", Author.class);
            query.setParameter("last_name",lastName + "%");
            return query.getResultList();
        }finally {
            em.close();
        }
    }

    @Override
    public Author getById(Long id) {
        return getEntityManager().find(Author.class,id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
//        TypedQuery<Author> query = getEntityManager().createQuery("SELECT a from Author a " +
//                " WHERE a.firstName = :first_name and a.lastName = :last_name", Author.class);

        try (EntityManager em = getEntityManager()) {
            TypedQuery<Author> query = em.createNamedQuery("find_by_name", Author.class);
            query.setParameter("first_name",firstName);
            query.setParameter("last_name",lastName);
            return query.getSingleResult();
        }
    }

    @Override
    public Author saveNewAuthor(Author author) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.flush();
        em.getTransaction().commit();
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        EntityManager em = getEntityManager();
        em.joinTransaction();
        em.merge(author);
        em.flush();
        em.clear();
        return em.find(Author.class,author.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Author author = em.find(Author.class,id);
        em.remove(author);
        em.flush();
        em.getTransaction().commit();
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }
}
