package com.example.mobile.repos;

import com.example.mobile.domain.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepos extends CrudRepository<Question, Long> {
}
