package com.example.mobile.repos;

import com.example.mobile.domain.Statistic;
import org.springframework.data.repository.CrudRepository;

public interface StatisticRepos extends CrudRepository<Statistic, Long> {

    Iterable<Statistic> findById_user(Integer id_user);
}
