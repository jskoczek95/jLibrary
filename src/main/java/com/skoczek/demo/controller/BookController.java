package com.skoczek.demo.controller;

import com.skoczek.demo.model.Book;
import com.skoczek.demo.model.User;
import com.skoczek.demo.service.BookService;

import com.skoczek.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}/show-books")
    public String listBooks(Model model, @PathVariable("id") Long id){

        model.addAttribute("book", bookService.getBookByOwner(id));
        return "list-books";
    }


    @PostMapping("/user/new-book")
    public String saveBook(@ModelAttribute Book book) {

        bookService.saveBook(book);
        return "redirect:/user/list";
    }

    @GetMapping("/user/{id}/new-book")
    public String showAddBookForm(Model model, @PathVariable Long id){

        User user = userService.findById(id);
        Book book = new Book();
        book.setUser(user);
        model.addAttribute("book", book);
        return "book-form";
    }

    @GetMapping("/user/{id}/search")
    public String searchByTitle(@RequestParam("searchedTitle") String theTitle, Model model){

        List<Book> books = bookService.searchBookByTitle(theTitle);
        model.addAttribute("book", books);
        return "list-books";
    }


}
