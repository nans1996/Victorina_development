package com.example.mobile.repos;

import com.example.mobile.domain.Answer;
import com.example.mobile.domain.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AnswerRepos extends CrudRepository<Answer, Long> {

 @Query("select a from Answer a where a.id_question = :id")
 List<Answer> findById_question(@Param("id") Integer id);
//      List<Answer> findById_question(Integer id);
//
}
