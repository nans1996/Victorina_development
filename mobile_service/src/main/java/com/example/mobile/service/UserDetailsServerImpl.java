package com.example.mobile.service;

import com.example.mobile.domain.Users;
import com.example.mobile.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class UserDetailsServerImpl implements UserDetailsService {

    @Autowired
    private UserRepos userRepos;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Users user = userRepos.findByLogin(login);

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), null);
    }
}
