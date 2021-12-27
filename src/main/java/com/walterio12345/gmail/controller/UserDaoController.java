package com.walterio12345.gmail.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walterio12345.gmail.dto.CharacterDTO;
import com.walterio12345.gmail.dto.UserDto;
import com.walterio12345.gmail.entity.UserDao;
import com.walterio12345.gmail.service.UserDaoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import javassist.NotFoundException;

@RestController
@RequestMapping(path = "usersMovie")
@Tag(name = "Users", description = "Operations for Users")
public class UserDaoController {

	@Autowired
	private UserDaoService userDaoService;
	
	@PostMapping
	public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) throws NotFoundException{
		
		if(userDto.getId()== 0L) {
			
			throw new NotFoundException("not ID");
		}
		
		return new ResponseEntity<>(userDaoService.save(userDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> findId(@PathVariable Long id) throws NotFoundException{
		
		return ResponseEntity.ok(userDaoService.findByID(id));
		
	}
	@PutMapping()
	public ResponseEntity<UserDto> update(@RequestBody @Valid UserDto userDto) throws NotFoundException{
		
		
		return ResponseEntity.ok(userDaoService.update(userDto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id)throws NotFoundException{
		userDaoService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> finAll(){
		return ResponseEntity.ok(userDaoService.findAll());
	}
}
