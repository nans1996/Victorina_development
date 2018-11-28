package com.example.mobile.controller;

import com.example.mobile.domain.Question;
import com.example.mobile.domain.Statistic;
import com.example.mobile.domain.Users;
import com.example.mobile.repos.QuestionRepos;
import com.example.mobile.repos.StatisticRepos;
import com.example.mobile.repos.UserRepos;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserController() {
    }

    @Autowired
    private UserRepos userRepos;
    @Autowired
    private QuestionRepos questionRepos;



    //работает
    @RequestMapping(value = "/get")
    public Iterable<Users> findAllUser() throws ParseException {
        Iterable<Users> users = userRepos.findAll();
        return users;
    }


//работает
    @RequestMapping(value = "/add", method = RequestMethod.POST)
<<<<<<< HEAD
    public String add(@Valid @RequestBody Users user){
     //   HttpHeaders http = new HttpHeaders();
        try {
            userRepos.save(user);
        }
        catch (Exception e){
            LOGGER.error("Ошибка добавления пользователя: "+ e);
        }
        return "успешно";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@Valid @RequestParam("login") String login, @Valid @RequestParam("password") String password, @Valid @RequestParam("email") String email,
                          @Valid @RequestParam("first_name") String first_name, @Valid @RequestParam("last_name") String last_name){
        //   HttpHeaders http = new HttpHeaders();
        try {

            Users user = new Users(login,password,email,first_name,last_name);
            userRepos.save(user);
        }
        catch (Exception e){
            LOGGER.error("Ошибка добавления пользователя: "+ e);
        }
        return "успешно";
=======
    public String add(@RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("email") String email,
                      @RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name) throws ParseException {
        Users user = new Users(login,password,email,first_name,last_name);
        userRepos.save(user);

        return "успешно";
    }

    //работает
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public Optional<Users> findUser(@RequestParam("id") Integer id) throws ParseException {
    Optional<Users> user =  userRepos.findById(id);
     return user;
>>>>>>> parent of 97f15ce... сервис
    }



    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@RequestParam("id") Integer id, @RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("email") String email,
                                    @RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name) {
        Optional<Users> user =  userRepos.findById(id);
        user.get().setLogin(login);
        user.get().setPassword(password);
        user.get().setEmail(email);
        user.get().setFirst_name(first_name);
        user.get().setLast_name(last_name);
        userRepos.save(user.get());
        return "успешно";
    }

    //авторизация
    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public Boolean findUser(@RequestParam("login") String login, @RequestParam("password") String password) throws ParseException {
        Users us = userRepos.findByLoginAndPassword(login, password);
        if (us.getId()!= null) {
            return true;
        }
        else {
            return false;
        }
    }

}
