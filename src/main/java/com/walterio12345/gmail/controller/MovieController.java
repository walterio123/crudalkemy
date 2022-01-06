package com.walterio12345.gmail.controller;

import java.util.List;
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

import com.walterio12345.gmail.dto.MovieDTO;
import com.walterio12345.gmail.service.MovieService;

import io.swagger.v3.oas.annotations.tags.Tag;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/movies")
@Tag(name = "Movies", description = "Operations for movies")
public class MovieController {

	
	private final MovieService movieService;

	@PostMapping
	public ResponseEntity<MovieDTO> create(@RequestBody @Valid MovieDTO movie) throws Exception {

		if (movie.getId() != null) {
			throw new NotFoundException("no send ID");
		}
		return new ResponseEntity<>(movieService.save(movie), HttpStatus.CREATED);
	}

	
	@GetMapping
	public ResponseEntity<List<MovieDTO>> finAll(@RequestParam(required = false) String title,
			@RequestParam(required = false) String order,
			@RequestParam(required = false) String gender) {

		if (title != null) {
			return ResponseEntity.ok(movieService.findAllTitle(title));
		}
		if (order != null) {
			Sort.Direction sortDirection = Sort.Direction.fromString(order);
			Sort sort = Sort.by(sortDirection, "creationDate");
			return ResponseEntity.ok(movieService.findAll(sort));
		}
		
		if(gender != null) {
			
			return ResponseEntity.ok(movieService.findAll(gender));
		}

		return ResponseEntity.ok(movieService.findAll());
	}
	

	@PutMapping
	public ResponseEntity<MovieDTO> update(@RequestBody @Valid MovieDTO movieDTO) throws Exception {

		return ResponseEntity.ok(movieService.update(movieDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") String idMovie) throws NotFoundException {
		if (movieService.findByID(idMovie) == null) {
			return ResponseEntity.notFound().build();
		} else {
			movieService.delete(idMovie);
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MovieDTO> findId(@PathVariable(value="id") String idMovie) throws NotFoundException {

		if (movieService.findByID(idMovie) == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(movieService.findByID(idMovie));
	}

}
