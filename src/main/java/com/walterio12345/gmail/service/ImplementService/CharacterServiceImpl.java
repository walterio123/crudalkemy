package com.walterio12345.gmail.service.ImplementService;

import java.util.List;
import java.util.Set;

import com.walterio12345.gmail.entity.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walterio12345.gmail.Repository.CharacterRepository;
import com.walterio12345.gmail.dto.CharacterDTO;
import com.walterio12345.gmail.mapper.CharacterMapper;
import com.walterio12345.gmail.service.CharacterService;

import javassist.NotFoundException;
import lombok.var;


@Service

public class CharacterServiceImpl implements CharacterService{
	
	@Autowired
	private  CharacterMapper characterMapper;
	
	@Autowired
	private  CharacterRepository characterRepository;
	
	
	
	@Override
	@Transactional
	public CharacterDTO save(CharacterDTO dto) throws Exception {
		if(characterRepository.existsByName(dto.getName())) {
			throw new NotFoundException("There is already a character with that name.. ");
		}
		Character character=characterMapper.toEntity(dto);
		characterRepository.save(character);
		return characterMapper.toDto(character,false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CharacterDTO> findAll() {
		
		return characterMapper.toDto(characterRepository.findAll());
	}


	@Override
	@Transactional
	public CharacterDTO update(CharacterDTO dto) throws Exception {
		var characterID=characterRepository.findById((dto.getId())).orElseThrow(()->new NotFoundException("There is no movie with that id"));
	
		var character=characterRepository.save(characterMapper.toEntity(dto));
		return characterMapper.toDto(character,false);
		
	}

	@Override
	@Transactional
	public void delete(String id) throws NotFoundException {
		if(!characterRepository.existsById(id)) {
			throw new NotFoundException("There is no character with that id");
		}else {
			characterRepository.deleteById(id);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<CharacterDTO> findAll(String name) {
		
		return characterMapper.toDto(characterRepository.findByNameContaining(name));
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<CharacterDTO> findAll(Integer age) {
		
		return characterMapper.toDto(characterRepository.findByAge(age));
	}

	@Override
	@Transactional(readOnly = true)
	public List<CharacterDTO> findAll(Set<String> movies) {
		
		return characterMapper.toDto(characterRepository.findByMoviesIdIn(movies));
	}

	@Override
	@Transactional(readOnly = true)
	public CharacterDTO findByID(String id) throws NotFoundException {
     var character=characterRepository.findById(id).orElseThrow(()->new NotFoundException("There is no character with that id"));
		
		return characterMapper.toDto(character,false) ;
	}

}
