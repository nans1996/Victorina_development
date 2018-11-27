package com.example.mobile.repos;

import com.example.mobile.domain.Answer;
import com.example.mobile.domain.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepos extends CrudRepository<Users, Integer> {

    @Query("select u from Users u where u.login = :login")
    Users findByLogin (@Param("login") String login);
}
