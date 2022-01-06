package com.walterio12345.gmail.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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

import com.walterio12345.gmail.Repository.GenderRepository;
import com.walterio12345.gmail.dto.GenderDTO;
import com.walterio12345.gmail.service.GenderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(path = "/genders",produces = APPLICATION_JSON_VALUE)
@Tag(name = "Gender",description = "Movie genre operations(CRUD)")
public class GenderController {
	@Autowired
	private GenderRepository genderRepository;
	@Autowired
	private GenderService genderService;
	
	@PostMapping
	public ResponseEntity<GenderDTO> create(@RequestBody @Valid GenderDTO gender) throws NotFoundException{
		
		if(gender.getId() != null) {
			throw new NotFoundException("not ID");
		}
		
		return new ResponseEntity<>(genderService.save(gender),HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<GenderDTO> update(@RequestBody @Valid GenderDTO gender) throws NotFoundException{
		
		return new ResponseEntity<>(genderService.update(gender),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id")String idGender) throws NotFoundException{
		
		if (genderService.findByID(idGender) == null) {
			return ResponseEntity.notFound().build();
		} else {
			genderService.delete(idGender);
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<List<GenderDTO>> findAll(){
		
		return ResponseEntity.ok(genderService.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<GenderDTO> findById(@PathVariable(value ="id" ) String idGender) throws NotFoundException{
		
		return ResponseEntity.ok().body(genderService.findByID(idGender));
	}
	
	@GetMapping("/name/{id}")
	public ResponseEntity<List<GenderDTO>> findByName(@PathVariable(value ="id" ) String Gender) throws NotFoundException{
		
		return ResponseEntity.ok().body(genderService.findAll(Gender));
	}
}
