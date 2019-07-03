package com.skoczek.jLibrary.dao;


import com.skoczek.jLibrary.model.Book;

import java.util.List;

public interface BookDAO {

    List<Book> getBooks();

    void saveBook(Book book);

    List<Book> getBookByOwner(Long id);

    void deleteBook(Long id);

    List<Book> searchBookByTitle(String title);

    Book findById(Long id);

}
