package com.walterio12345.gmail.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walterio12345.gmail.entity.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender, String>{

	
     
	List<Gender> findByNameContaining(String name);
}
