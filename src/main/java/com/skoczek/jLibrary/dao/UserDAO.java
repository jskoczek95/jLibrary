package com.skoczek.jLibrary.dao;

import com.skoczek.jLibrary.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getUsers();

    void saveUser(User user);

    List<User> searchUserByFirstName(String firstName);

    User findById(Long id);

    User findByUserName(String userName);

    void deleteUser(Long id);


}
