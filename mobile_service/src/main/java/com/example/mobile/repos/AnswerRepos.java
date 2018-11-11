package com.example.mobile.repos;

import com.example.mobile.domain.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepos extends CrudRepository<Answer, Long> {
}
