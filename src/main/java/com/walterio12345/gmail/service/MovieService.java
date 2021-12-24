package com.walterio12345.gmail.service;

import java.util.List;
import java.util.UUID;

import com.walterio12345.gmail.dto.GenderDTO;
import com.walterio12345.gmail.dto.MovieDTO;

import javassist.NotFoundException;

import org.springframework.data.domain.Sort;

public interface MovieService {

	List<MovieDTO>  findAll(String idGender);
	
	List<MovieDTO> findAllTitle(String title);
	
	List<MovieDTO> findAll(Sort sort);
	
	MovieDTO save(MovieDTO dto) throws NotFoundException;
	
	List<MovieDTO> findAll();
	
	MovieDTO findByID (String id) throws NotFoundException;
	
	MovieDTO update( MovieDTO dto) throws NotFoundException;
	
	void delete(String id) throws NotFoundException;
	
}
