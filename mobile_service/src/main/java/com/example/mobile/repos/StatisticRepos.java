package com.example.mobile.repos;



import com.example.mobile.domain.Statistic;
import com.example.mobile.domain.Users;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticRepos extends CrudRepository<com.example.mobile.domain.Statistic, Long> {


    @Query("select s from Statistic s where s.id_user = :id_user")
    List<Statistic> findById_user (@Param("id_user") Integer id_user);

    @Query("select s.count_truth, s.date from Statistic s")
    Iterable<Statistic> getStatisticByCount_truthAndDate ();
//   // Iterable<Statistic> findById_user(Integer id_user);

}
