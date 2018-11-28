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
    public String addStatistic(@RequestParam String date, @RequestParam int count_truth, @RequestParam Users id_user) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date docDate= format.parse(date);

        Statistic st = new Statistic(docDate, count_truth, id_user);
        statisticRepos.save(st);
        return "успешно";
    }

    //вернуть всю статистику
@RequestMapping(value = "get")
    public Iterable<Statistic> getStatistic() throws ParseException {
     Iterable<Statistic> st = statisticRepos.findAll();
     return st;

}

//вывести по пользователю

//@RequestMapping(value = "/getByIdUser", method = RequestMethod.POST)
//    public List<Statistic> getByUser(@RequestParam("id_user") Integer id_user) throws ParseException {
//        List<Statistic> st = statisticRepos.findById_user(id_user);
//        return st;
//}

////@RequestMapping(value = "/getByIdUser", method = RequestMethod.POST)
////    public Iterable<Statistic> getByUser(@RequestParam("id_user") Integer id_user) throws ParseException {
////        Iterable<Statistic> st = statisticRepos.findById_user(id_user);
////        return st;
////}



}
