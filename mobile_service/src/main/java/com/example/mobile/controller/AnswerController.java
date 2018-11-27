package com.example.mobile.controller;

import com.example.mobile.domain.Answer;
import com.example.mobile.domain.Question;
import com.example.mobile.domain.Statistic;
import com.example.mobile.domain.Users;
import com.example.mobile.repos.AnswerRepos;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value = "/getByIdQuestion", method = RequestMethod.POST)
 public List<Answer> getByIdQuestion(@RequestParam("id_question") Integer id_question){
     List<Answer> a = answerRepos.findById_question(id_question);
     return a;
 }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Answer addAnswer(@RequestBody Answer answer) throws ParseException {
        Answer st = new Answer(answer.getId_question(), answer.getDescription(), answer.getResult());
        answerRepos.save(st);
        return st;
    }

}
