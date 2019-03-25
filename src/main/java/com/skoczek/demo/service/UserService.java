package com.skoczek.demo.service;

import com.skoczek.demo.model.User;

import java.util.List;

public interface UserService{

    List<User> getUsers();

    void saveUser(User user);

    List<User> searchUserByFirstName(String firstName);

    User findById(Long id);

    User findByUserName(String userName);

    void addWithDefaultRole(User user);

    void deleteUser(Long id);

}
