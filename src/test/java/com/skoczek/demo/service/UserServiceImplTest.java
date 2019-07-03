package com.skoczek.demo.service;

import com.skoczek.jLibrary.dao.UserDAO;
import com.skoczek.jLibrary.model.User;
import com.skoczek.jLibrary.model.UserRole;
import com.skoczek.jLibrary.repository.UserRepository;
import com.skoczek.jLibrary.repository.UserRoleRepository;
import com.skoczek.jLibrary.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    public static final String DEFAULT_ROLE = "USER_ROLE";

    @Mock
    private UserDAO userDAO;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRoleRepository roleRepository;

    private User user;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() throws Exception {

        user = new User();

        MockitoAnnotations.initMocks(this);

        user.setFirstName("Jakub");
        user.setLastName("Skoczek");
        user.setEmail("test@test.com");
        user.setId(1L);
        user.setPassword("testtest");

    }

    @Test
    public void getUsers() {

        List<User> list = new ArrayList<>();
        list.add(user);

        when(userDAO.getUsers()).thenReturn(list);

        List<User> userList = userDAO.getUsers();

        assertEquals(1, userList.size());
        verify(userDAO, times(1)).getUsers();
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

        List<User> userList = userDAO.searchUserByFirstName(anyString());

        assertNotNull(userList);
        assertEquals(userList.size(), 1);

    }

    @Test
    public void findById() {

        User testUser = new User();
        testUser.setId(1L);
        testUser.setFirstName("Kuba");

        when(userDAO.findById(anyLong())).thenReturn(testUser);

        User user = userDAO.findById(1L);

        assertNotNull(user);
        assertEquals(user.getFirstName(), "Kuba");
        verify(userDAO, never()).getUsers();
    }


    @Test
    public void findByUserName() {

        when(userDAO.findByUserName(anyString())).thenReturn(user);

        User theUser = userDAO.findByUserName("Jakub");

        assertEquals("Skoczek", theUser.getLastName());
    }

    @Test
    public void addWithDefaultRole() {

        userService.addWithDefaultRole(user);

        verify(userRepository, times(1)).save(user);

    }

    @Test
    public void isAlreadyRegistered() {

        userService.isAlreadyRegistered(anyString());

        verify(userRepository, times(1)).findByEmail(anyString());
        when(userRepository.findByEmail(anyString())).thenReturn(new User());

        User mail = userRepository.findByEmail("test@test.com");
        assertNotNull(mail);
        assertTrue(userService.isAlreadyRegistered(anyString()));

    }

    @Test
    public void deleteUser() {

        userDAO.deleteUser(anyLong());
        verify(userDAO, times(1)).deleteUser(anyLong());
    }
}