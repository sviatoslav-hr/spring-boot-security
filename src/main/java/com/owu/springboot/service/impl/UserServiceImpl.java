package com.owu.springboot.service.impl;

import com.owu.springboot.dao.UserDAO;
import com.owu.springboot.models.User;
import com.owu.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void save(User user) {
        if (user != null) {
            userDAO.save(user);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userDAO.findAll();
        if (userList.size() == 0) {
            List<User> users = Arrays.asList(new User(), new User());
            return users;
        }
        return userList;
    }

    @Override
    public User findOneById(int id) {
        if (id > 0) return userDAO.findById(id).get();
        else return new User();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.findByUsername(s);
    }
}
