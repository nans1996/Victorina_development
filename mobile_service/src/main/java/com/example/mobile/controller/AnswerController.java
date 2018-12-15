package com.example.mobile.controller;

import com.example.mobile.domain.Answer;
import com.example.mobile.domain.Question;
import com.example.mobile.domain.UserAnswer;
import com.example.mobile.domain.Users;
import com.example.mobile.repos.AnswerRepos;
import com.example.mobile.repos.UserAnswerRepos;
import com.example.mobile.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/answer")
public class AnswerController {

    @Autowired
    private AnswerRepos answerRepos;

    @Autowired
    private UserAnswerRepos userAnswerRepos;

//    @Autowired
//    private UserRepos userRepos;


    //вывод по вопросу
//
//    @RequestMapping(value = "/getByIdQuestion", method = RequestMethod.GET)
// public List<Answer> getByIdQuestion(@RequestParam("id_question") Question id_question){
//     List<Answer> a = answerRepos.findById_question(id_question);
//     return a;
// }

//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public UserAnswer addUserAnswer(@RequestParam("login") String login, @RequestParam("id_answer") Answer id_answer) {
//        Users us = userRepos.findByLogin(login);
//        UserAnswer ua = new UserAnswer(us, id_answer);
//        userAnswerRepos.save(ua);
//        return ua;
//    }

    @RequestMapping(value = "/getByIdQuestion", method = RequestMethod.GET)
 public String getByIdQuestion(@RequestParam("id_question") Question id_question){
     List<Answer> a = answerRepos.findById_question(id_question);
     return a.toString();
 }

}
