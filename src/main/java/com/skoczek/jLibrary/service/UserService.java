package com.skoczek.jLibrary.service;

import com.skoczek.jLibrary.dto.UserDTO;
import com.skoczek.jLibrary.dto.UserRegistrationDTO;

public interface UserService {

    UserDTO registerUser(UserRegistrationDTO userRegistrationDTO);
    UserDTO findById(Long id);
}
