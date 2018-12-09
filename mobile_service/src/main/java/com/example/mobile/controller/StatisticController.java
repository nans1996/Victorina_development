package com.example.mobile.controller;


import com.example.mobile.domain.Statistic;
import com.example.mobile.domain.Users;
import com.example.mobile.repos.StatisticRepos;
import org.springframework.beans.factory.annotation.Autowired;
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

   //добавление статистики
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStatistic( @RequestParam int count_truth, @RequestParam Users id_user) throws ParseException {
       Date date = new Date();
        SimpleDateFormat Format = new SimpleDateFormat("dd.MM.yyyy");
        String docDate = Format.format(date);

        Statistic st = new Statistic(docDate, count_truth, id_user);
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
    public String getByUser(@RequestParam("id_user") Users id_user) throws ParseException {
        List<Statistic> st = statisticRepos.findById_user(id_user);
        return st.toString();
}

////@RequestMapping(value = "/getByIdUser", method = RequestMethod.POST)
////    public Iterable<Statistic> getByUser(@RequestParam("id_user") Integer id_user) throws ParseException {
////        Iterable<Statistic> st = statisticRepos.findById_user(id_user);
////        return st;
////}



}
