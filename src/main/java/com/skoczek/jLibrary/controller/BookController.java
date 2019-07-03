package com.skoczek.jLibrary.controller;

import com.skoczek.jLibrary.model.Book;
import com.skoczek.jLibrary.model.User;
import com.skoczek.jLibrary.service.BookService;
import com.skoczek.jLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/user/books")
public class BookController {

    private BookService bookService;
    private UserService userService;

    @Autowired
    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/{id}/books")
    public String listBooks(Model model, @PathVariable("id") Long id) {

        model.addAttribute("book", bookService.getBookByOwner(id));
        return "books/list-books";
    }


    @PostMapping("/add")
    public String saveBook(@ModelAttribute Book book) {

        bookService.saveBook(book);
        return "redirect:/user/list";

    }

    @GetMapping("/{id}/add")
    public String showAddBookForm(Model model, @PathVariable Long id) {

        User user = userService.findById(id);
        Book book = new Book();
        book.setUser(user);
        model.addAttribute("book", book);
        return "books/book-form";
    }

    @GetMapping("/{id}/search")
    public String searchByTitle(@RequestParam("searchedTitle") String theTitle, Model model) {

        List<Book> books = bookService.searchBookByTitle(theTitle);
        model.addAttribute("book", books);
        return "books/list-books";
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {

        bookService.deleteBook(id);

        return "redirect:/user/list";
    }

    @GetMapping("/{id}/update")
    public String updateBook(@PathVariable Long id, Model model) {

        Book book = bookService.findById(id);
        model.addAttribute("book", book);

        return "books/update-book-form";
    }

    @GetMapping("/collection")
    public String getAllBooks(Model model) {

        model.addAttribute("thebooks", bookService.getBooks());
        return "books/books-all";
    }


}
