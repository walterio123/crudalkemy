package com.walterio12345.gmail.mapper;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.walterio12345.gmail.dto.UserDto;
import com.walterio12345.gmail.entity.UserDao;

import javassist.NotFoundException;


@Component
public class UserDaoMapper implements Mapper<UserDao, UserDto>{

	@Override
	public UserDto toDto( UserDao e) {
		UserDto dto=new UserDto();
		dto.setId(e.getId());
		dto.setUsername(e.getUsername());
		dto.setEmail(e.getEmail());
//		dto.setPassword(e.getPassword());
		return dto;
	}

	@Override
	public UserDao toEntity(@NotNull UserDto dto) throws NotFoundException {
	
		UserDao userDao=new UserDao();
		userDao.setId(dto.getId());
		userDao.setUsername(dto.getUsername());
		userDao.setEmail(dto.getEmail());
		userDao.setPassword(dto.getPassword());
		
		return userDao;
	}

	

}
