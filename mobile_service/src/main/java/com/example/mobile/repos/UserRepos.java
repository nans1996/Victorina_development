package com.example.mobile.repos;

import org.springframework.data.repository.CrudRepository;

public interface UserRepos extends CrudRepository<Users, Integer> {

<<<<<<< HEAD
    @Query("select u from Users u where u.login = :login")
    Users findByLogin (@Param("login") String login);

    @Query("select u from Users u where u.login = :login and u.password=:password")
    Users findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
=======
>>>>>>> parent of 97f15ce... сервис
}
