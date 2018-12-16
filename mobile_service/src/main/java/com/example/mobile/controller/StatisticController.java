package com.example.mobile.controller;


import com.example.mobile.domain.Statistic;
import com.example.mobile.domain.Users;
import com.example.mobile.repos.StatisticRepos;
import com.example.mobile.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticRepos statisticRepos;

    @Autowired
    private UserRepos userRepos;
   //добавление статистики
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStatistic( @RequestParam("count_truth") int count_truth, @RequestParam("login") String login) throws ParseException {
       Date date = new Date();
        SimpleDateFormat Format = new SimpleDateFormat("dd.MM.yyyy");
        String docDate = Format.format(date);
        Users us = userRepos.findByLogin(login);
        Statistic st = new Statistic(docDate, count_truth, us);
        statisticRepos.save(st);
        return "успешно";
    }

    //вернуть всю статистику
@RequestMapping(value = "/get")
    public Iterable<Statistic> getStatistic() throws ParseException {
     Iterable<Statistic> st = statisticRepos.findAll();
     return st;

}

    @RequestMapping(value = "/getCD", method = RequestMethod.GET)
    public Iterable<Statistic> getCountDate() throws ParseException {
        Iterable<Statistic> st = statisticRepos.getStatisticByCount_truthAndDate();
        return st;

    }

//вывести по пользователю

@RequestMapping(value = "/getByIdUser", method = RequestMethod.GET)
    public String getByUser(@RequestParam("login") String login) throws ParseException {
        Users us = userRepos.findByLogin(login);
        List<Statistic> st = statisticRepos.findById_user(us);
        return st.toString();
}

////@RequestMapping(value = "/getByIdUser", method = RequestMethod.POST)
////    public Iterable<Statistic> getByUser(@RequestParam("id_user") Integer id_user) throws ParseException {
////        Iterable<Statistic> st = statisticRepos.findById_user(id_user);
////        return st;
////}



}
