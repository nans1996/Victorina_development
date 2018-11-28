package com.example.mobile.controller;


import com.example.mobile.domain.Statistic;
import com.example.mobile.domain.Users;
import com.example.mobile.repos.StatisticRepos;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticRepos statisticRepos;

    static final Logger LOGGER = Logger.getLogger(UserController.class);

   //добавление статистики
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Statistic> addStatistic(@RequestBody Statistic statistic)  {
        HttpHeaders http = new HttpHeaders();
        try {
            Date date = new Date();
            SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
            String docDate= formatDate.format(date);

            Statistic st = new Statistic(docDate,statistic.getCount_truth(),statistic.getId_user());
            statisticRepos.save(st);
            return new ResponseEntity<>(st, http, HttpStatus.CREATED);
        }
       catch (Exception e){
            LOGGER.error("Ошибка добавления статистики: "+ e);
       }
        return new ResponseEntity<>(statistic, http, HttpStatus.CREATED);
    }

    //вернуть всю статистику надо ли?
@RequestMapping(value = "get")
    public Iterable<Statistic> getStatistic() throws ParseException {
     Iterable<Statistic> st = statisticRepos.findAll();
     return st;

}

//вывести по пользователю
@RequestMapping(value = "/getByIdUser", method = RequestMethod.POST)
    public List<Statistic> getByUser(@RequestParam("id_user") Integer id_user) throws ParseException {
        List<Statistic> st = statisticRepos.findById_user(id_user);
        return st;
}


}
