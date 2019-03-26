package com.skoczek.demo.service;

import com.skoczek.demo.dao.UserDAO;
import com.skoczek.demo.model.User;
import com.skoczek.demo.model.UserRole;
import com.skoczek.demo.repository.UserRepository;
import com.skoczek.demo.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private UserRepository userRepository;
    private UserRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private static final String DEFAULT_ROLE = "ROLE_USER";

    @Autowired
    public UserServiceImpl(UserDAO userDAO, UserRepository userRepository, UserRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }


    @Override
    public List<User> searchUserByFirstName(String firstName) {
        return userDAO.searchUserByFirstName(firstName);
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }


    @Override
    public User findByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }



    public void addWithDefaultRole(User user) {
        UserRole defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }
}
