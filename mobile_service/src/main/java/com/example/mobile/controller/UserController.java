package com.example.mobile.controller;

import com.example.mobile.domain.Question;
import com.example.mobile.domain.Statistic;
import com.example.mobile.domain.Users;
import com.example.mobile.repos.QuestionRepos;
import com.example.mobile.repos.StatisticRepos;
import com.example.mobile.repos.UserRepos;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.Valid;
import java.util.Optional;
import org.apache.log4j.Logger;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserController() {
    }

    @Autowired
    private UserRepos userRepos;


    static final Logger LOGGER = Logger.getLogger(UserController.class);

    //работает надо ли?
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Iterable<Users> findAllUser() throws ParseException {
        Iterable<Users> users = userRepos.findAll();
        return users;
    }


//работает
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Users> add(@Valid @RequestBody Users user){
        HttpHeaders http = new HttpHeaders();
        try {
            userRepos.save(user);
        }
        catch (Exception e){
            LOGGER.error("Ошибка добавления пользователя: "+ e);
        }
        return new ResponseEntity<>(user, http, HttpStatus.CREATED);
    }

    //проверка логина
    @RequestMapping(value = "/findByName", method = RequestMethod.GET)
    public Users findUser(@RequestParam("login") String login) throws ParseException {
  Users us = userRepos.findByLogin(login);
     return us;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<Users> editUser(@Valid @RequestBody Users us

    ) {
        HttpHeaders http = new HttpHeaders();
        try {

            Optional<Users> user = userRepos.findById(us.getId());
            user.get().setLogin(us.getLogin());
            user.get().setPassword(us.getPassword());
            user.get().setEmail(us.getEmail());
            user.get().setFirst_name(us.getFirst_name());
            user.get().setLast_name(us.getLast_name());
            userRepos.save(user.get());
            return new ResponseEntity<>(user.get(),http,HttpStatus.UPGRADE_REQUIRED);
        }
        catch (Exception e){
            LOGGER.error("Ошибка обновления пользователя "+ e);
        }
        return new ResponseEntity<>(us,http,HttpStatus.UPGRADE_REQUIRED);
    }



}
