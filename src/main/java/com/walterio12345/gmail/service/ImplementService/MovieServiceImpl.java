package com.walterio12345.gmail.service.ImplementService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.walterio12345.gmail.Repository.MovieRepository;
import com.walterio12345.gmail.dto.MovieDTO;
import com.walterio12345.gmail.mapper.MovieMapper;
import com.walterio12345.gmail.service.MovieService;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.var;

@Service
//@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
	@Autowired
	private  MovieRepository movieRepository;
	@Autowired
	private  MovieMapper movieMapper;
	
	
	@Override
	@Transactional
	public MovieDTO save(MovieDTO dto) throws Exception {
		
		var movie=movieMapper.toEntity(dto);
		movieRepository.save(movie);
		return movieMapper.toDto(movie,false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MovieDTO> findAll() {
		List<MovieDTO>movies=movieMapper.toDto(movieRepository.findAll(),false);
		return movies;
	}

	@Override
	@Transactional(readOnly = true)
	public MovieDTO findByID(String id) throws NotFoundException {
		var movie=movieRepository.findById(id).orElseThrow(()->new NotFoundException("There is no movie with that id"));
		
		return movieMapper.toDto(movie,false);
	}

	@Override
	@Transactional
	public MovieDTO update(MovieDTO dto) throws Exception {
		
		var movieID=movieRepository.findById(dto.getId()).orElseThrow(()->new NotFoundException("There is no movie with that id"));
		var movie= movieRepository.save(movieMapper.toEntity(dto));
		return movieMapper.toDto(movie,false);
	}

	@Override
	@Transactional
	public void delete(String id) throws NotFoundException {
		if(!movieRepository.existsById(id)) {
			throw new NotFoundException("There is no movie with that id");
		}
		movieRepository.deleteById(id);
		
	}


	@Override
	@Transactional(readOnly = true)
	public List<MovieDTO> findAllTitle(String title) {
		

		return movieMapper.toDto(movieRepository.findByTitle(title),false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MovieDTO> findAll(Sort sort) {
		
		return movieMapper.toDto(movieRepository.findAll(sort),false);
	}
	

	@Override
	@Transactional(readOnly = true)
	public List<MovieDTO> findAll(String idGender) {
	
		return movieMapper.toDto(movieRepository.findByGenderId(idGender),false);
	}

	
}
