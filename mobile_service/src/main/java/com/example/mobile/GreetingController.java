package com.example.mobile;

import com.example.mobile.domain.Question;
import com.example.mobile.domain.Statistic;
import com.example.mobile.domain.Users;
import com.example.mobile.repos.QuestionRepos;
import com.example.mobile.repos.StatisticRepos;
import com.example.mobile.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class GreetingController {

    public GreetingController() {
    }

    @Autowired
    private UserRepos userRepos;
    @Autowired
    private QuestionRepos questionRepos;


    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Users> users = userRepos.findAll();
        model.put("users", users);
        return "main";
    }

    @GetMapping("/question")
    public String questionAll(Map<String, Object> model) {
        Iterable<Question> q = questionRepos.findAll();
        model.put("questions", q);
        return "question";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("email") String email,
                      @RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name) {
        Users user = new Users(login,password,email,first_name,last_name);
        userRepos.save(user);
//        Iterable<Users> users = userRepos.findAll();
//        model.put("users", users);
        return "успешно";
    }

    @PostMapping("/question")
    public String addQuestion(@RequestParam String description, Map<String, Object> model) {
        Question ques = new Question(description);
        questionRepos.save(ques);
        Iterable<Question> questions = questionRepos.findAll();
        model.put("questions", questions);

        return "question";
    }



}
