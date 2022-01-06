package com.walterio12345.gmail.mapper;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.walterio12345.gmail.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.walterio12345.gmail.dto.CharacterDTO;
import com.walterio12345.gmail.entity.Character;
import com.walterio12345.gmail.entity.Movie;

import javassist.NotFoundException;

@Component
public class CharacterMapper{
	
	@Autowired
	private MovieMapper movieMapper;
	private boolean loadMovie;
	static List<CharacterDTO>toDTOList( List<Character>characters){
		return characters.stream().map(character ->{
			CharacterDTO dto=new CharacterDTO();
			dto.setId(character.getId());
			dto.setName(character.getName());
			dto.setImage(character.getImage());
			return dto;
		}).collect(Collectors.toList());
	}
	
	

	public CharacterDTO toDto (Character character,boolean loadMovie) {
		CharacterDTO dto=new CharacterDTO();
		dto.setId(character.getId());
		dto.setImage(character.getImage());
		dto.setName(character.getName());
		dto.setAge(character.getAge());
		if(loadMovie){
			List<MovieDTO>movieDto=movieMapper.toDto(character.getMovies(),false);
					dto.setMovies(movieDto);
		}


		return dto;
	}


	public Character toEntity(CharacterDTO dto) throws Exception {
		Character character=new Character();
		if (dto.getId()!=null) character.setId(dto.getId());
		character.setName(dto.getName());
		character.setAge(dto.getAge());
		character.setImage(dto.getImage());
		List<Movie>movies= (List<Movie>) movieMapper.toEntity((MovieDTO) dto.getMovies());
		return character;
		
	}

	    public List<CharacterDTO> toDto(List<Character> characters) {
	        return toDTOList(characters);
	    }




}
