package com.walterio12345.gmail.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.walterio12345.gmail.Repository.CharacterRepository;
import com.walterio12345.gmail.Repository.GenderRepository;
import com.walterio12345.gmail.dto.CharacterDTO;
import com.walterio12345.gmail.dto.MovieDTO;
import com.walterio12345.gmail.entity.Movie;
import com.walterio12345.gmail.entity.Character;
import com.walterio12345.gmail.entity.Gender;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.var;

@Component
//@RequiredArgsConstructor
public class MovieMapper implements Mapper<Movie, MovieDTO> {
	@Autowired
	private CharacterRepository characterRepository;
	@Autowired
	private  GenderRepository genderRepository;
	@Autowired
	private   GenderMapper genderMapper;
	@Autowired
	private  CharacterMapper characterMapper;	
	
	static List<MovieDTO> toDTOList( List<Movie> movies) {
        return movies.stream().map(movie -> {
            MovieDTO dto = new MovieDTO();
            dto.setId(movie.getId());
            dto.setTitle(movie.getTitle());
            dto.setImage(movie.getImage());
            dto.setCreationDate(movie.getCreationDate());
            return dto;
        }).collect(Collectors.toList());
        
    }
	
	@Override
	public MovieDTO toDto(Movie movie) {
		var dto=new MovieDTO();
		dto.setId(movie.getId());
		dto.setCreationDate(movie.getCreationDate());
		dto.setImage(movie.getImage());
		dto.setQualification(movie.getQualification());
		dto.setTitle(movie.getTitle());
		dto.setGender(genderMapper.toDto(movie.getGender()));
		dto.setCharacters(characterMapper.toDto(movie.getCharacters()));
		return dto;
	}

	@Override
	public Movie toEntity(MovieDTO dto) throws NotFoundException {
		Movie movie=new Movie();
		if (dto.getId() != null) movie.setId((dto.getId()));
		movie.setId(dto.getId());
		movie.setCreationDate(dto.getCreationDate());
		movie.setImage(dto.getImage());
		movie.setQualification(dto.getQualification());
		movie.setTitle(dto.getTitle());
		/////TODO arreglar llegada de datos 
		System.out.println("ACA VA EL GENERO"+dto.getGenderId());
		//Gender gender=genderRepository.findById(dto.getId()).orElseThrow(()->new NotFoundException("no Gender id"));
		
		var gender=genderRepository.findById("e161426d-6128-497c-bfe0-c1f549cf2097").orElseThrow(()->new NotFoundException("no Gender id"));
		movie.setGender(gender);//.orElseThrow(()->new NotFoundException("no Gender id"));
		////
		movie.setCharacters(entityCharacters(dto.getCharactersIds()));//
		return movie;
		
	}
		
	   @Override
	    public List<MovieDTO> toDto(List<Movie> movies) {
	        return toDTOList(movies);
	    }

	   private List<Character> entityCharacters(List<String> charactersIds) {  
		 List<Character>listaCharacters=new ArrayList<Character>();
		   for (String charid : charactersIds) {
			  Character character = characterRepository.findById(charid).get();
			  listaCharacters.add(character);
		}    
	        return listaCharacters;
	              
	    }

	   

}
