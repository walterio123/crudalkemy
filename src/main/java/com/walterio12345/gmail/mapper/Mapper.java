package com.walterio12345.gmail.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import javassist.NotFoundException;



public interface Mapper <E,D>{
	
	//entity to DTO
	D toDto( E e);
	
	//entityList to DTOList 
	default List<D> toDto( List<E> es) {
        return es.stream().map(this::toDto).collect(Collectors.toList());
    }
	//DTO to entity
	E toEntity( D dto) throws NotFoundException;
	
	//DTOList to entityList
	
	default List<E>toEntity( List<D> dtos) throws NotFoundException{
		List<E> es=new ArrayList<>();
		for (D d : dtos) {
			es.add(toEntity(d));
		}
		return es;
	}
}
