package com.example.mobile.repos;

import com.example.mobile.domain.UserAnswer;
import org.springframework.data.repository.CrudRepository;

public interface UserAnswerRepos extends CrudRepository<UserAnswer, Integer> {
}
