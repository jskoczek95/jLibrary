package com.skoczek.demo.dao;

import com.skoczek.demo.model.Book;
import com.skoczek.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> getBooks() {
        Query query = entityManager.createQuery("SELECT p FROM Book p", Book.class);
        return query.getResultList();
    }

    @Override
    public void saveBook(Book book) {
        entityManager.merge(book);
    }

    @Override
    public List<Book> getBookByOwner(Long id) {
        Query query = entityManager.createQuery("SELECT p FROM Book p WHERE p.user.id =:theId", Book.class);
        query.setParameter("theId", id);
        List<Book> books = query.getResultList();
        return books;
    }

    @Override
    public void deleteBook(Long id) {
        Query query = entityManager.createQuery("DELETE FROM Book WHERE id=:bookId");
        query.setParameter("bookId", id);
        query.executeUpdate();
    }

    @Override
    public List<Book> searchBookByTitle(String searchedTitle) {
        Query query = null;
        if(searchedTitle != null && searchedTitle.trim().length() > 0){
            query = entityManager.createQuery("FROM Book WHERE lower(title) like :theTitle", Book.class);
            query.setParameter("theTitle", searchedTitle.toLowerCase());
        } else {
            query = entityManager.createQuery("FROM Book", Book.class);
        }
        List<Book> books = query.getResultList();
        return books;
    }
}
