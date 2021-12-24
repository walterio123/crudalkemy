package com.walterio12345.gmail.Repository;

import org.springframework.data.repository.CrudRepository;

import com.walterio12345.gmail.entity.UserDao;

public interface UserRepository extends CrudRepository<UserDao, Integer>{

	UserDao findByUsername(String username);
}
