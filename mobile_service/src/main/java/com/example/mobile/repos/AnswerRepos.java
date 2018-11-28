package com.example.mobile.repos;

import com.example.mobile.domain.Answer;
import com.example.mobile.domain.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AnswerRepos extends CrudRepository<Answer, Long> {

<<<<<<< HEAD
 @Query("select a from Answer a where a.id_question = :id_question")
 List<Answer> findById_question(@Param("id_question") Question id);
//      List<Answer> findById_question(Integer id);
//
=======
    //List<Answer> findById_question(Question id_question);
>>>>>>> parent of 97f15ce... сервис
}
