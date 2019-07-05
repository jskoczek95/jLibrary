package com.skoczek.jLibrary.service;

import com.skoczek.jLibrary.domain.User;
import com.skoczek.jLibrary.dto.UserDTO;
import com.skoczek.jLibrary.dto.UserRegistrationDTO;
import com.skoczek.jLibrary.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDTO registerUser(UserRegistrationDTO userRegistrationDTO) {

        User user = userRepository.findByEmail(userRegistrationDTO.getEmail());

        if (user == null) {
            user = new User();
            user.setFirstName(userRegistrationDTO.getFirstName());
            user.setLastName(userRegistrationDTO.getLastName());
            user.setEmail(userRegistrationDTO.getEmail());
            user.setEncryptedPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));

            user = userRepository.save(user);

            ModelMapper modelMapper = new ModelMapper();

            return modelMapper.map(user, UserDTO.class);
        } else throw new RuntimeException("Record already exists!");

    }

    @Override
    public UserDTO findById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();

            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(user, UserDTO.class);
        } else throw new RuntimeException("User with given Id doesnt exist!");
    }
}
