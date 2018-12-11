package com.example.mobile.service;


import com.example.mobile.domain.Users;
import com.example.mobile.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class UserService implements UserServiceInterface{

@Autowired
    private UserRepos userRepos;


    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void save(Users user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepos.save(user);
    }

    @Override
    public Users findByLogin(String login) {
        return userRepos.findByLogin(login);
    }
}
