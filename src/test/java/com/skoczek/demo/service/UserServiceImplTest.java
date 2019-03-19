package com.skoczek.demo.service;

import com.skoczek.demo.dao.UserDAO;
import com.skoczek.demo.model.User;
import com.skoczek.demo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
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

    UserServiceImpl userService;

    @Mock
    UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);


    }

    @Test
    public void getUsers() {

        userDAO.getUsers();

        verify(userDAO,times(1)).getUsers();
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

        when(userDAO.findById(anyLong())).thenReturn(userReturned);
        verify(userDAO, times(1)).findById(anyLong());
        verify(userDAO, never()).getUsers();

    }


    @Test
    public void findByUserName() {
    }

    @Test
    public void addWithDefaultRole() {
    }
}