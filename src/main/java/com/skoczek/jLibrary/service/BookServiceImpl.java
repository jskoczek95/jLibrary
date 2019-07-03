package com.skoczek.jLibrary.service;

import com.skoczek.jLibrary.dao.BookDAO;
import com.skoczek.jLibrary.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> getBooks() {
        return bookDAO.getBooks();
    }

    @Override
    public void saveBook(Book book) {
        bookDAO.saveBook(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookDAO.deleteBook(id);
    }

    @Override
    public List<Book> searchBookByTitle(String title) {
        return bookDAO.searchBookByTitle(title);
    }

    @Override
    public List<Book> getBookByOwner(Long id) {
        return bookDAO.getBookByOwner(id);
    }

    @Override
    public Book findById(Long id) {
        return bookDAO.findById(id);
    }
}
