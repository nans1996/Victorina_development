package com.example.mobile.repos;

import com.example.mobile.domain.Answer;
import com.example.mobile.domain.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AnswerRepos extends CrudRepository<Answer, Long> {

    //List<Answer> findById_question(Question id_question);
}
