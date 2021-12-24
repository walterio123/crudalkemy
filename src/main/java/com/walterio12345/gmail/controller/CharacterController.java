package com.walterio12345.gmail.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walterio12345.gmail.dto.CharacterDTO;
import com.walterio12345.gmail.service.CharacterService;


import io.swagger.v3.oas.annotations.tags.Tag;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "characters")
@Tag(name = "Characters", description = "Operations for movie characters")
//@RequiredArgsConstructor
public class CharacterController {
	
	@Autowired
	private  CharacterService characterService ;
	
	
	@PostMapping
	public ResponseEntity<CharacterDTO> create(@RequestBody @Valid CharacterDTO character) throws NotFoundException{
		
		if(character.getId()!=null) {
			
			throw new NotFoundException("not ID");
		}
		
		return new ResponseEntity<>(characterService.save(character),HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<CharacterDTO>> finAll(@RequestParam (required = false) String name,
													@RequestParam (required = false) Integer age,
													@RequestParam(required = false) Set<String>movies){
		
		if(name != null) {
			return ResponseEntity.ok(characterService.findAll(name));
		}
		
		if (age != null) {
			return ResponseEntity.ok(characterService.findAll(age));
		}
		if(movies != null) {
			return ResponseEntity.ok(characterService.findAll(movies));
		}
		
		return ResponseEntity.ok(characterService.findAll());
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<CharacterDTO> findId(@PathVariable String id) throws NotFoundException{
		
		return ResponseEntity.ok(characterService.findByID(id));
		
	}
	@PutMapping()
	public ResponseEntity<CharacterDTO> update(@RequestBody @Valid CharacterDTO characterDTO) throws NotFoundException{
		
		
		return ResponseEntity.ok(characterService.update(characterDTO));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id)throws NotFoundException{
		characterService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
}
