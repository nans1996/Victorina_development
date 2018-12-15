package com.example.mobile.controller;

import com.example.mobile.domain.Users;
import com.example.mobile.repos.UserRepos;
import com.example.mobile.service.JwtTokenProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepos userRepos;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    static final Logger LOGGER = Logger.getLogger(UserController.class);

    //работает надо ли?
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Iterable<Users> findAllUser() throws ParseException {
        Iterable<Users> users = userRepos.findAll();
        return users;
    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@Valid @RequestParam("login") String login, @Valid @RequestParam("password") String password, @Valid @RequestParam("email") String email,
                          @Valid @RequestParam("first_name") String first_name, @Valid @RequestParam("last_name") String last_name){
        try {

            Users user = new Users(login,password,email,first_name,last_name);
            userRepos.save(user);

        }
        catch (Exception e){
            LOGGER.error("Ошибка добавления пользователя: "+ e);
        }
        return "успешно";
    }


    //вход
    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    public String findUser(@RequestParam("login") String login, @RequestParam("password") String password) throws ParseException {
        Users us = userRepos.findByLoginAndPassword(login, password);
        if (us.getId()!= null) {
            return jwtTokenProvider.createToken(login);
        }
        else {
            return "НЕТ!";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(
            @Valid @RequestParam("login") String login, @Valid @RequestParam("password") String password, @Valid @RequestParam("email") String email,
                           @Valid @RequestParam("first_name") String first_name, @Valid @RequestParam("last_name") String last_name

    ) {
        try {

            Users user = userRepos.findByLogin(login);
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setFirst_name(first_name);
            user.setLast_name(last_name);
            userRepos.save(user);
            return user.toString();
        }
        catch (Exception e){
            LOGGER.error("Ошибка обновления пользователя "+ e);
        }
        return "error";
    }


    @RequestMapping(value = "/getByLogin", method = RequestMethod.POST)
    public String findUser(@RequestParam("login") String login) throws ParseException {
        Users users = userRepos.findByLogin(login);
        return users.toString();
    }

}
