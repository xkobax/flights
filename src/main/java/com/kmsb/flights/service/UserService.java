package com.kmsb.flights.service;

import com.kmsb.flights.persistence.entity.User;
import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByName(String name);

    User findById(BigDecimal id);

    void saveUser(User user);

    void deleteUser(User user);

}
