package com.example.mobile.service;

import com.example.mobile.domain.Users;

public interface UserServiceInterface {

    void save (Users user);
    Users findByLogin(String login);
}
