package com.example.mobile.controller;

import com.example.mobile.domain.Answer;
import com.example.mobile.repos.AnswerRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/answer")
public class AnswerController {

    @Autowired
    private AnswerRepos answerRepos;

    //вывод по вопросу
 public Iterable<Answer> getByIdQuestion(@RequestParam("id_question") Integer id_question){
     Iterable<Answer> a = answerRepos.findAll();
     return a;
 }
}
