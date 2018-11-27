package com.example.mobile.repos;



import com.example.mobile.domain.Statistic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatisticRepos extends CrudRepository<Statistic, Long> {

  //  List<Statistic> findById_user(Integer id_user);
}
