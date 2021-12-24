package com.walterio12345.gmail.service;

import java.util.List;


import com.walterio12345.gmail.dto.GenderDTO;

import javassist.NotFoundException;


public interface GenderService {

	List<GenderDTO> findAll(String name);
	
	GenderDTO save(GenderDTO dto) throws NotFoundException;
	
	List<GenderDTO> findAll();
	
	GenderDTO findByID (String id) throws NotFoundException;
	
	GenderDTO update( GenderDTO dto) throws NotFoundException;
	
	void delete(String id) throws NotFoundException;

}
