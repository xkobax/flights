package com.kmsb.flights.service.impl;

import com.kmsb.flights.persistence.dao.UserDAO;
import com.kmsb.flights.persistence.entity.User;
import com.kmsb.flights.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public User findById(BigDecimal id) {
        return userDAO.findById(id);
    }

    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }
}
