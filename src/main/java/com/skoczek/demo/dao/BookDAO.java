package com.skoczek.demo.dao;


import com.skoczek.demo.model.Book;

import java.util.List;

public interface BookDAO {

    List<Book> getBooks();

    void saveBook(Book book);

    List<Book> getBookByOwner(Long id);

    void deleteBook(Long id);

    List<Book> searchBookByTitle(String title);

}
