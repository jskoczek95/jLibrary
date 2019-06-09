package com.skoczek.demo.controller;

import com.skoczek.demo.model.Book;
import com.skoczek.demo.model.User;
import com.skoczek.demo.service.BookService;
import com.skoczek.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class BookControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;
    private Book book;
    private User user;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        book = new Book();
        user = new User();
        user.setFirstName("Kuba");
        user.setLastName("Test");
        book.setUser(user);
        book.setTitle("GOT");
        book.setAuthor("test");
        book.setId(1L);

    }

    @Test
    @WithMockUser
    public void listBooks() throws Exception {

        mockMvc.perform(get("/user/books/{id}/books", 1))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void saveBook() throws Exception {

        mockMvc.perform(post("/user/books/add")
                .param("author", book.getAuthor())
                .param("title", book.getTitle()))
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    public void showAddBookForm() {
    }

    @Test
    @WithMockUser
    public void searchByTitle() throws Exception {

        List<Book> list = new ArrayList<>();
        list.add(book);

        when(bookService.searchBookByTitle(anyString())).thenReturn(list);

        String theTitle = bookService.searchBookByTitle("GOT").get(0).getTitle();

        mockMvc.perform(get("/user/books/{id}/search", 1L)
                .param("searchedTitle", "GOT"))
                .andExpect(view().name("books/list-books"))
                .andExpect(status().isOk());

        assertNotNull(theTitle);
    }

    @Test
    @WithMockUser
    public void deleteBook() throws Exception {

        mockMvc.perform(get("/user/books/{id}/delete", 1L))
                .andExpect(redirectedUrl("/user/list"))
                .andExpect(view().name("redirect:/user/list"));

    }

    @Test
    @WithMockUser
    public void updateBook() throws Exception {

        when(bookService.findById(1L)).thenReturn(book);

        Book theBook = bookService.findById(1L);

        mockMvc.perform(get("/user/books/{id}/update", 1L))
                .andExpect(status().isOk());

        assertNotNull(theBook);
    }

    @Test
    @WithMockUser
    public void getAllBooks() throws Exception {

        mockMvc.perform(get("/user/books/collection"))
                .andExpect(view().name("books/books-all"))
                .andExpect(status().isOk());
    }
}