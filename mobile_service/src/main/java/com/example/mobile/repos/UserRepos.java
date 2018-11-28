package com.example.mobile.repos;

import com.example.mobile.domain.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepos extends CrudRepository<Users, Integer> {


    @Query("select u from Users u where u.login = :login")
    Users findByLogin (@Param("login") String login);

    @Query("select u from Users u where u.login = :login and u.password=:password")
    Users findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

}
