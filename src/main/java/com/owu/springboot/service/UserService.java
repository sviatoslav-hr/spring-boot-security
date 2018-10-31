package com.owu.springboot.service;

import com.owu.springboot.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    void save(User user);

    List<User> findAll();

    User findOneById(int id);

}
