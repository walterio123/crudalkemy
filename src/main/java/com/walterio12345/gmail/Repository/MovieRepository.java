package com.walterio12345.gmail.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walterio12345.gmail.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String>{
	
	List<Movie> findByTitle (String title);
	
	List<Movie>findByGenderId(String id);

	
}
