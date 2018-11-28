package com.example.mobile.repos;


<<<<<<< HEAD

import com.example.mobile.domain.Statistic;
import com.example.mobile.domain.Users;
import org.springframework.data.jpa.repository.Query;
=======
import com.sun.org.glassfish.external.statistics.Statistic;
>>>>>>> parent of 97f15ce... сервис
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StatisticRepos extends CrudRepository<Statistic, Long> {

<<<<<<< HEAD
    @Query("select s from Statistic s where s.id_user = :id_user")
    List<Statistic> findById_user (@Param("id_user") Integer id_user);
=======
   // Iterable<Statistic> findById_user(Integer id_user);
>>>>>>> parent of 97f15ce... сервис
}
