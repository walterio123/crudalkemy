package com.walterio12345.gmail.service;

import java.util.List;
import java.util.Set;

import com.walterio12345.gmail.dto.CharacterDTO;

import javassist.NotFoundException;

public interface CharacterService  {
	
	List<CharacterDTO> findAll(String name);
	
	List<CharacterDTO> findAll(Integer age);
	
	List<CharacterDTO> findAll(Set<String>movies);
	
	
	CharacterDTO save(CharacterDTO dto) throws NotFoundException;
	
	List<CharacterDTO> findAll();
	
	CharacterDTO findByID (String id) throws NotFoundException;
	
	CharacterDTO update( CharacterDTO dto) throws NotFoundException;
	
	void delete(String id) throws NotFoundException;

}
