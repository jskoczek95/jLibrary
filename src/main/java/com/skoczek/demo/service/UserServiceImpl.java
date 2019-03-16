package com.skoczek.demo.service;

import com.skoczek.demo.dao.UserDAO;
import com.skoczek.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

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

}
