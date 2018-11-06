package com.example.mobile.repos;

import com.example.mobile.domain.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepos extends CrudRepository<Users, Long> {
}
