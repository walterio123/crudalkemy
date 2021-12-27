package com.walterio12345.gmail.service;

import java.util.List;

import com.walterio12345.gmail.dto.CharacterDTO;
import com.walterio12345.gmail.dto.UserDto;

import javassist.NotFoundException;

public interface UserDaoService {
		
	boolean existsByUsername(String name);
	
	boolean existsByEmail(String email);
	
	UserDto save(UserDto dto) throws NotFoundException;
	
	List<UserDto> findAll();
	
	UserDto findByID (Long id) throws NotFoundException;
	
	UserDto update( UserDto dto) throws NotFoundException;
	
	void delete( Long id) throws NotFoundException;
}
