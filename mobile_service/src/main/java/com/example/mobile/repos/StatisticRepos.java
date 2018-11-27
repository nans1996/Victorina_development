package com.example.mobile.repos;



import com.example.mobile.domain.Statistic;
import com.example.mobile.domain.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticRepos extends CrudRepository<Statistic, Long> {

    @Query("select s from Statistic s where s.id_user = :id_user")
    List<Statistic> findById_user (@Param("id_user") Integer id_user);
}
