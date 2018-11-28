package com.example.mobile.controller;

import com.example.mobile.domain.Answer;
import com.example.mobile.domain.Question;
import com.example.mobile.domain.Statistic;
import com.example.mobile.domain.Users;
import com.example.mobile.repos.AnswerRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/answer")
public class AnswerController {

    @Autowired
    private AnswerRepos answerRepos;

    //вывод по вопросу
    @RequestMapping(value = "/getByIdQuestion", method = RequestMethod.GET)
 public List<Answer> getByIdQuestion(@RequestParam("id_question") Question id_question){
     List<Answer> a = answerRepos.findById_question(id_question);
     return a;
 }

}
