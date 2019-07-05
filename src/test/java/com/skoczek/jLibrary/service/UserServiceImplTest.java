package com.skoczek.jLibrary.service;

import com.skoczek.jLibrary.domain.User;
import com.skoczek.jLibrary.dto.UserDTO;
import com.skoczek.jLibrary.dto.UserRegistrationDTO;
import com.skoczek.jLibrary.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private static final String ENCRYPTED_PASSWORD = "533iji35";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void registerUserWhenNotNull() {

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setEmail("test@test.com");
        userRegistrationDTO.setFirstName("test");
        userRegistrationDTO.setLastName("test");
        userRegistrationDTO.setPassword("1234");

        when(userRepository.findByEmail(anyString())).thenReturn(any(User.class));

        User user = userRepository.findByEmail(userRegistrationDTO.getEmail());

        assertThrows(RuntimeException.class, () -> userRepository.findByEmail(user.getEmail()));
    }

    @Test
    void registerUserWhenNull() {

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setEmail("test@test.com");
        userRegistrationDTO.setFirstName("test");
        userRegistrationDTO.setLastName("test");
        userRegistrationDTO.setPassword("1234");
        User user = new User();
        user.setEmail(userRegistrationDTO.getEmail());
        user.setFirstName(userRegistrationDTO.getFirstName());

        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(passwordEncoder.encode(anyString())).thenReturn(ENCRYPTED_PASSWORD);
        when(userRepository.save(any(User.class))).thenReturn(user);


        UserDTO createdUser = userService.registerUser(userRegistrationDTO);
        assertNotNull(createdUser);
        assertEquals(createdUser.getEmail(), userRegistrationDTO.getEmail());
        verify(passwordEncoder, times(1)).encode("1234");

        verify(userRepository, times(1)).save(any(User.class));

    }
}