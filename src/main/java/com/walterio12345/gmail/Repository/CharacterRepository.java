package com.walterio12345.gmail.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walterio12345.gmail.entity.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, String>{

	boolean existsByName(String name);
	
	List<Character> findByNameContaining(String name);
	
	List<Character> findByAge (Integer age);
	
	List<Character> findByMoviesIdIn(Collection<String>moviesIds);
	
}
