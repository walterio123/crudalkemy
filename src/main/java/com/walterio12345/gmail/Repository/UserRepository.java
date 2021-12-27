package com.walterio12345.gmail.Repository;

import org.springframework.data.repository.CrudRepository;

import com.walterio12345.gmail.entity.UserDao;

public interface UserRepository extends CrudRepository<UserDao, Long>{

	UserDao findByUsername(String username);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
}
