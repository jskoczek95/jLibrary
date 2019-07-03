package com.skoczek.demo.controller;

import com.skoczek.jLibrary.controller.UserController;
import com.skoczek.jLibrary.model.User;
import com.skoczek.jLibrary.service.UserService;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private User user;
    private User theUser;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        user = new User();
        user.setFirstName("Kuba");
        user.setLastName("Skoczek");
        user.setPassword("test");
        user.setEmail("test@test.com");
        user.setId(1L);

        theUser = new User();
        theUser.setFirstName("Kuba");
        theUser.setLastName("Skoczek");
        theUser.setPassword("test");
        theUser.setEmail("test@test.com");
        theUser.setId(2L);
    }

    @Test
    @WithMockUser
    public void userList() throws Exception {

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/user-list"))
                .andReturn();

        List<User> list = new ArrayList<>();
        list.add(new User());

        when(userService.getUsers()).thenReturn(list);

        List<User> userList = userService.getUsers();

        verify(userService, times(1)).getUsers();
        assertEquals(1, userList.size());

    }

    @Test
    @WithMockUser
    public void saveUser() throws Exception {

        mockMvc.perform(post("/user/save")
                .param("firstName", "Jakub")
                .param("lastName", "Skoczek")
                .param("email", "test@test.com")
                .param("password", "test"))
                .andExpect(redirectedUrl("/user/list"));

        userService.saveUser(user);

        verify(userService, times(1)).saveUser(user);
    }


    @Test
    @WithMockUser
    public void searchByName() throws Exception {

        List<User> list = new ArrayList<>();
        list.add(user);

        when(userService.searchUserByFirstName(anyString())).thenReturn(list);

        String theName = userService.searchUserByFirstName("Kuba").get(0).getFirstName();

        mockMvc.perform(get("/user/search")
                .param("searchedUser", "Kuba"))
                .andExpect(status().isOk());

        assertEquals("Kuba", theName);
        verify(userService, times(1)).searchUserByFirstName(anyString());
    }

    @Test
    public void register() throws Exception {

        mockMvc.perform(get("/user/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register/new-register"));
    }

    @Test
    @WithMockUser
    public void addUserSuccess() throws Exception {

        mockMvc.perform(post("/user/register")
                .param("firstName", "test")
                .param("lastName", "test")
                .param("email", "test")
                .param("password", "test"))
                .andExpect(view().name("register/register-success"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void addUserHasErrors() throws Exception {

        mockMvc.perform(post("/user/register")
                .param("firstName", "")
                .param("lastName", "test")
                .param("email", "test@test.com")
                .param("password", "test"))
                .andExpect(view().name("register/new-register"))
                .andExpect(status().isOk());

    }


    @Test
    @WithMockUser
    public void deleteUser() throws Exception {

        mockMvc.perform(get("/user/{id}/delete", 2L))
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void deleteUserAsAdmin() throws Exception {

        mockMvc.perform(get("/user/admin/{id}/delete", 1L))
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    @WithMockUser
    public void updateUser() throws Exception {

        when(userService.findById(1L)).thenReturn(user);

        User res = userService.findById(1L);

        mockMvc.perform(get("/user/{id}/update", 2L))
                .andExpect(status().isOk());

        assertNotNull(res);
    }
}