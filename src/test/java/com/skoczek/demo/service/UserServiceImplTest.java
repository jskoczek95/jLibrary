package com.skoczek.demo.service;

import com.skoczek.demo.dao.UserDAO;
import com.skoczek.demo.model.User;
import com.skoczek.demo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    private UserDAO userDAO;

    private User user;

    @Before
    public void setUp() throws Exception {

        user = new User();

        MockitoAnnotations.initMocks(this);

        user.setFirstName("Jakub");
        user.setLastName("Skoczek");
        user.setEmail("test@test.com");
        user.setId(1L);


    }

    @Test
    public void getUsers() {

        List<User> list = new ArrayList<>();
        list.add(user);

        when(userDAO.getUsers()).thenReturn(list);

        assertEquals(list.get(0), userDAO.getUsers().get(0));
        assertEquals(list.size(), userDAO.getUsers().size());


    }

    @Test
    public void saveUser() {

        userDAO.saveUser(user);
        verify(userDAO, times(1)).saveUser(user);
    }


    @Test
    public void searchUserByFirstName() {

        List<User> list = new ArrayList<>();
        list.add(user);
        when(userDAO.searchUserByFirstName(anyString())).thenReturn(list);

        assertNotNull(list);
        assertEquals(list.size(), 1);
        assertEquals(userDAO.searchUserByFirstName("Jakub").size(), 1);
        assertEquals(userDAO.searchUserByFirstName(null).size(), 0);

    }

    @Test
    public void findById() {

        when(userDAO.findById(anyLong())).thenReturn(user);

        assertNotNull(user);
        assertEquals(user.getFirstName(), "Jakub");
        verify(userDAO, never()).getUsers();

    }


    @Test
    public void findByUserName() {
    }

    @Test
    public void addWithDefaultRole() {
    }
}