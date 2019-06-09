package com.skoczek.demo.dao;

import com.skoczek.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT p FROM User p", User.class);
        return query.getResultList();
    }


    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }


    @Override
    public List<User> searchUserByFirstName(String firstName) {
        Query query = null;
        if (firstName != null && firstName.trim().length() > 0) {
            query = entityManager.createQuery("FROM User WHERE lower(first_name) like:theName", User.class);
            query.setParameter("theName", firstName.toLowerCase());
        } else {
            query = entityManager.createQuery("SELECT p FROM User p", User.class);
            query.getResultList();
        }
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public User findById(Long id) {
        User user = entityManager.find(User.class, new Long(id));
        if (user == null) {
            throw new EntityNotFoundException("Cant find user's id" + id);
        }
        return user;
    }

    @Override
    public User findByUserName(String userName) {
        Query query = entityManager.createQuery("SELECT p FROM User p WHERE first_name=:theName", User.class);
        query.setParameter("theName", userName);
        return (User) query.getSingleResult();
    }

    @Override
    public void deleteUser(Long id) {
        User user = findById(id);
        entityManager.remove(user);
    }
}
