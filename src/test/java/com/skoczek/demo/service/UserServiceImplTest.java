package com.skoczek.demo.service;

import com.skoczek.demo.dao.UserDAO;
import com.skoczek.demo.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    UserServiceImpl userService;

    @Mock
    UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userService = new UserServiceImpl();
    }

    @Test
    public void getUsers() {
    }

    @Test
    public void saveUser() {
    }

    @Test
    public void saveUserAndBook() {
    }

    @Test
    public void searchUserByFirstName() {
    }

    @Test
    public void findById() {
        User user = new User();
        user.setId(1L);

        User userReturned = userDAO.findById(1L);

        verify(userDAO, times(1)).findById(anyLong());
        verify(userDAO, never()).getUsers();

    }
}