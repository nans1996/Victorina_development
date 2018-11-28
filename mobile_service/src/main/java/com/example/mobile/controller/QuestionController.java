package com.example.mobile.controller;

import com.example.mobile.domain.Question;
import com.example.mobile.domain.Users;
import com.example.mobile.repos.QuestionRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

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

    //добавить???
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam("description") String description) throws ParseException {
        Question q = new Question(description);
        questionRepos.save(q);

        return "успешно";
    }
}
