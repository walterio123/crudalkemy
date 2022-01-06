package com.walterio12345.gmail.mapper;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.walterio12345.gmail.Repository.CharacterRepository;
import com.walterio12345.gmail.Repository.GenderRepository;
import com.walterio12345.gmail.dto.CharacterDTO;
import com.walterio12345.gmail.dto.MovieDTO;
import com.walterio12345.gmail.entity.Movie;
import com.walterio12345.gmail.entity.Character;
import com.walterio12345.gmail.entity.Gender;

import javassist.NotFoundException;

import lombok.var;

@Component

public class MovieMapper {
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private GenderMapper genderMapper;
    @Autowired
    private CharacterMapper characterMapper;
    boolean loadCharacter;

    static List<MovieDTO> toDTOList(List<Movie> movies) {
        return movies.stream().map(movie -> {
            MovieDTO dto = new MovieDTO();
            dto.setId(movie.getId());
            dto.setTitle(movie.getTitle());
            dto.setImage(movie.getImage());
            dto.setCreationDate(movie.getCreationDate());
            return dto;
        }).collect(Collectors.toList());

    }


    public MovieDTO toDto(Movie movie, boolean loadCharacter) {
        var dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setCreationDate(movie.getCreationDate());
        dto.setImage(movie.getImage());
        dto.setQualification(movie.getQualification());
        dto.setTitle(movie.getTitle());
        dto.setGender(genderMapper.toDto(movie.getGender()));

        if (loadCharacter) {
            List<CharacterDTO> characterDTOS = (List<CharacterDTO>) characterMapper.toDto((Character) movie.getCharacters(), false);
            dto.setCharacters(characterDTOS);
        }

        return dto;
    }


    public Movie toEntity(MovieDTO dto) throws Exception {
        Movie movie = new Movie();
        if (dto.getId() != null) movie.setId((dto.getId()));
        movie.setId(dto.getId());
        movie.setCreationDate(dto.getCreationDate());
        movie.setImage(dto.getImage());
        movie.setQualification(dto.getQualification());
        movie.setTitle(dto.getTitle());


        Gender gender = genderMapper.toEntity(dto.getGender());
        movie.setGender(gender);

        List<Character> charactersList = entityCharacters(dto.getCharactersIds());

        movie.setCharacters((Set<Character>) charactersList);//
        return movie;

    }

    public List<Movie> toEntityList(List<MovieDTO> dtos) throws NotFoundException {
        List<Movie> movies = new ArrayList<>();
        for (MovieDTO dto : dtos) {
            Movie movie = new Movie();
            movie.setId(dto.getId());
            movie.setTitle(dto.getTitle());
            movie.setQualification(dto.getQualification());
            movie.setCreationDate(dto.getCreationDate());
            Gender gender = genderMapper.toEntity(dto.getGender());
            movie.setGender(gender);
            List<Character> characters = entityCharacters(dto.getCharactersIds());
            movie.setCharacters((Set<Character>) characters);
            movies.add(movie);

        }
        return movies;
    }


    public List<MovieDTO> toDto(List<Movie> movies, Boolean loadCharacter) {
        List<MovieDTO> dtos = new ArrayList<>();
        for (Movie entity : movies) {
            dtos.add(this.toDto(entity, false));
        }

        return dtos;
    }

    private List<Character> entityCharacters(List<String> charactersIds) {
        List<Character> listaCharacters = new ArrayList<Character>();
        for (String characters : charactersIds) {
            Optional<Character> respOptional = characterRepository.findById(characters);
            if (respOptional.isPresent()) {
                Character character = respOptional.get();
                listaCharacters.add(character);
            }
        }
        return listaCharacters;
    }


}
