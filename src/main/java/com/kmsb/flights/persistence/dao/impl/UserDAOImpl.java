package com.kmsb.flights.persistence.dao.impl;

import com.kmsb.flights.persistence.dao.AbstractDAO;
import com.kmsb.flights.persistence.dao.UserDAO;
import com.kmsb.flights.persistence.entity.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("userDAO")
public class UserDAOImpl extends AbstractDAO<BigDecimal, User> implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }

    @Override
    public User findByName(String name) {
        return sessionFactory.getCurrentSession().get(User.class, name);
    }

    @Override
    public User findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    public void saveUser(User user) {
        persist(user);
    }

    @Override
    public void deleteUser(User user) {
        delete(user);
    }

}
