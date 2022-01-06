package com.walterio12345.gmail.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.walterio12345.gmail.dto.GenderDTO;
import com.walterio12345.gmail.entity.Gender;

import javassist.NotFoundException;

@Component
public class GenderMapper implements Mapper<Gender, GenderDTO>{


	
	@Override
	public GenderDTO toDto(Gender gender) {
		
		GenderDTO genderDTO=new GenderDTO();
		genderDTO.setId(gender.getId().toString());
		genderDTO.setName(gender.getName());
		genderDTO.setDeleted(gender.isDeleted());
		return genderDTO;
	}

	@Override
	public Gender toEntity(GenderDTO dto) throws NotFoundException {
		
		Gender gender=new Gender();
		if (dto.getId()!=null) gender.setId((dto.getId()));
		gender.setName(dto.getName());
		gender.setDeleted(dto.isDeleted());
		return gender;
	}

	public List<GenderDTO>toListOfDTO(List<Gender>genders){
		List<GenderDTO>dtos=new ArrayList<>();
		for (Gender gender:genders) {
			dtos.add(this.toDto(gender));
		}
		return dtos;
	}
	public List<Gender>toListOfEntity(List<GenderDTO>gendersDTO) throws NotFoundException {
		List<Gender>entity=new ArrayList<>();
		for (GenderDTO dto:gendersDTO) {
			entity.add(this.toEntity(dto));
		}
		return entity;
	}
}
