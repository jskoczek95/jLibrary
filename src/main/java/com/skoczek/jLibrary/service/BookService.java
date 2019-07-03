package com.skoczek.jLibrary.service;

import com.skoczek.jLibrary.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooks();

    void saveBook(Book book);

    void deleteBook(Long id);

    List<Book> getBookByOwner(Long id);

    List<Book> searchBookByTitle(String title);

    Book findById(Long id);
}
