package com.example.mobile.controller;

import com.example.mobile.domain.Statistic;
import com.example.mobile.repos.StatisticRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class StatisticController {

    @Autowired
    private StatisticRepos statisticRepos;

    @GetMapping("/statistic")
    public String statisticAll(Map<String, Object> model) {
        Iterable<Statistic> st = statisticRepos.findAll();
        model.put("statistics", st);
        return "statistic";
    }

    @PostMapping("/statistic")
    public String addStatistic(@RequestParam String date, @RequestParam int count_truth, @RequestParam Integer id_user, Map<String, Object> model) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date docDate= format.parse(date);

        Statistic st = new Statistic(docDate, count_truth, id_user);
        statisticRepos.save(st);
        Iterable<Statistic> statistics = statisticRepos.findAll();
        model.put("statistics", statistics);

        return "statistic";
    }

//    @PostMapping
//    public void deleteStatistic(@RequestParam Integer id_statistic){
//       Statistic st = (Statistic) statisticRepos.findAllById(id_statistic);
//       statisticRepos.delete(st);
//    }




}
