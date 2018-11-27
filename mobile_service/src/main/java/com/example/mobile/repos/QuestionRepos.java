package com.example.mobile.repos;

import com.example.mobile.domain.Answer;
import com.example.mobile.domain.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepos extends CrudRepository<Question, Integer> {

//    @Query("select  q from Question q where q.idQuestion = :id")
//    List<Question> findById_question(@Param("id") Integer id);
//     List<Answer> findById_question(Integer id);
//

}
