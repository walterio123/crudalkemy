package com.walterio12345.gmail.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.walterio12345.gmail.Repository.MovieRepository;
import com.walterio12345.gmail.dto.CharacterDTO;
import com.walterio12345.gmail.entity.Character;
import com.walterio12345.gmail.entity.Movie;

import javassist.NotFoundException;

@Component
public class CharacterMapper implements Mapper<Character, CharacterDTO>{
	
	@Autowired
	private MovieRepository movieRepository;
	
	static List<CharacterDTO>toDTOList(@NotNull List<Character>characters){
		return characters.stream().map(character ->{
			CharacterDTO dto=new CharacterDTO();
			dto.setId(character.getId());
			dto.setName(character.getName());
			dto.setImage(character.getImage());
			return dto;
		}).collect(Collectors.toList());
	}
	
	
	//TODO faltan las listas cuando esten las relaciones
	@Override
	public CharacterDTO toDto (Character character) {
		CharacterDTO dto=new CharacterDTO();
		dto.setId(character.getId());
		dto.setImage(character.getImage());
		dto.setName(character.getName());
		dto.setAge(character.getAge());
		
		if(!character.getMovies().isEmpty() && character.getMovies()!=null) {
		dto.setMovies(MovieMapper.toDTOList(character.getMovies()));
		//character.setMovies(movieRepository.findAll());Prueba//
		}
		return dto;
	}

	@Override
	public Character toEntity(CharacterDTO dto) throws NotFoundException {
		Character character=new Character();
		if (dto.getId()!=null) character.setId(dto.getId());
		character.setName(dto.getName());
		character.setAge(dto.getAge());
		character.setImage(dto.getImage());
		
		return character;
		
	}
	  @Override
	    public List<CharacterDTO> toDto(List<Character> characters) {
	        return toDTOList(characters);
	    }



}
