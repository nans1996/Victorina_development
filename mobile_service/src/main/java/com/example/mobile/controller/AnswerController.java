package com.example.mobile.controller;

import com.example.mobile.domain.Answer;
import com.example.mobile.repos.AnswerRepos;
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



    //вывод по вопросу
//
//    @RequestMapping(value = "/getByIdQuestion", method = RequestMethod.GET)
// public List<Answer> getByIdQuestion(@RequestParam("id_question") Question id_question){
//     List<Answer> a = answerRepos.findById_question(id_question);
//     return a;
// }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Answer addAnswer(@RequestBody Answer answer) {

        Answer st = new Answer(answer.getId_question(), answer.getDescription(), answer.getResult());
        answerRepos.save(st);
        return st;
    }

//    @RequestMapping(value = "/getByIdQuestion", method = RequestMethod.POST)
// public List<Answer> getByIdQuestion(@RequestParam("id_question") Question id_question){
//     List<Answer> a = answerRepos.findById_question(id_question);
//     return a;
// }

}
