package com.walterio12345.gmail.service.ImplementService;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.walterio12345.gmail.Repository.GenderRepository;
import com.walterio12345.gmail.dto.GenderDTO;
import com.walterio12345.gmail.mapper.GenderMapper;
import com.walterio12345.gmail.service.GenderService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.var;


@Service
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService{

	
	private final GenderRepository genderRepository;
	
	
	private final GenderMapper genderMapper;
	
	@Override
	@Transactional
	public GenderDTO save(GenderDTO dto) throws NotFoundException {
		
		return genderMapper.toDto(genderRepository.save(genderMapper.toEntity(dto)));
	}

	@Override
	@Transactional(readOnly = true)
	public List<GenderDTO> findAll() {
		
		return genderMapper.toDto(genderRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public GenderDTO findByID(String id) throws NotFoundException {
		
		var idGender=genderRepository.findById(id).orElseThrow(()->new NotFoundException("There is no gender with that id"));
		return genderMapper.toDto(idGender);
	}

	@Override
	@Transactional
	public GenderDTO update(GenderDTO dto) throws NotFoundException {
		if(!genderRepository.existsById((dto.getId()))) {
			throw new NotFoundException("There is no gender with that id");
		}
		
		return genderMapper.toDto(genderRepository.save(genderMapper.toEntity(dto)));
	}

	@Override
	@Transactional
	public void delete(String id) throws NotFoundException {
		
		genderRepository.deleteById(id);
		
	}

	@Override
	public List<GenderDTO> findAll(String name) {
		
		return genderMapper.toDto(genderRepository.findByNameContaining(name));
	}


}
