package com.example.mobile.controller;

import com.example.mobile.domain.Question;
import com.example.mobile.domain.Users;
import com.example.mobile.repos.QuestionRepos;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    private QuestionRepos questionRepos;

    //вывести все
    @RequestMapping(value = "/get")
    public Iterable<Question> questionAll() {
       return questionRepos.findAll();
    }

 //???
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Optional<Question> findByIdQuestion(@RequestParam ("id") Integer id) throws ParseException {
Optional<Question> q = questionRepos.findById(id);
return q;
    }
}
