package com.skoczek.jLibrary.service;

import com.skoczek.jLibrary.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    void saveUser(User user);

    List<User> searchUserByFirstName(String firstName);

    User findById(Long id);

    void addWithDefaultRole(User user);

    void deleteUser(Long id);

    boolean isAlreadyRegistered(String email);

}
