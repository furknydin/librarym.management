package com.frknydin.librarym.management.DAO.book;

import com.frknydin.librarym.management.model.entity.Author;
import com.frknydin.librarym.management.model.entity.Book;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDaoImpl implements BookDao{
    private final EntityManagerFactory emf;

    public BookDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Book findBookByTitleNative(String title) {
        try (EntityManager em = getEntityManager()) {
            Query query = em.createNativeQuery("SELECT * FROM book b WHERE b.title = :title", Book.class);
            query.setParameter("title",title);

            return (Book) query.getSingleResult();
        }
    }

    @Override
    public Book findBookByTitleCriteria(String title) {
        try (EntityManager em = getEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

            Root<Book> root = criteriaQuery.from(Book.class);
            ParameterExpression<String> titleParam = criteriaBuilder.parameter(String.class);

            Predicate titlePre =  criteriaBuilder.equal(root.get("title"), titleParam);

            criteriaQuery.select(root).where(criteriaBuilder.and(titlePre));

            TypedQuery<Book> query = em.createQuery(criteriaQuery);
            query.setParameter(titleParam,title);

            return query.getSingleResult();
        }
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Book> query = em.createNamedQuery("find_all_book", Book.class);
            return query.getResultList();
        }
    }

    @Override
    public Book findBookByIsbn(String isbn) {
//        EntityManager em = getEntityManager();
//
//        try {
//            TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class);
//            query.setParameter("isbn",isbn);
//            return query.getSingleResult();
//        }finally {
//            em.close();
//        }

        try (EntityManager em = getEntityManager()) {
            TypedQuery<Book> query = em.createNamedQuery("find_book_by_isbn", Book.class);
            query.setParameter("isbn",isbn);
            return query.getSingleResult();
        }
    }

    @Override
    public Book getById(Long id) {
        EntityManager entityManager = getEntityManager();
        Book book = entityManager.find(Book.class,id);
        return book;
    }

    @Override
    public Book findBookByTitle(String title) {
        EntityManager em = getEntityManager();
        TypedQuery<Book> query = em.createQuery(" SELECT b from Book b WHERE b.title = :title" ,Book.class);
        query.setParameter("title",title);
        return query.getSingleResult();
    }

    @Override
    public Book saveNewBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.flush();
        em.getTransaction().commit();

        return book;
    }

    @Override
    public Book updateBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(book);
        em.flush();
        em.getTransaction().commit();
        return em.find(Book.class,book.getId());
    }

    @Override
    public void deleteBookById(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Book book = em.find(Book.class,id);
        em.remove(book);
        em.flush();
        em.getTransaction().commit();
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
