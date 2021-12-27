package com.walterio12345.gmail.service.ImplementService;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walterio12345.gmail.Repository.UserRepository;
import com.walterio12345.gmail.dto.UserDto;
import com.walterio12345.gmail.entity.UserDao;
import com.walterio12345.gmail.mapper.UserDaoMapper;
import com.walterio12345.gmail.service.EmailService;
import com.walterio12345.gmail.service.UserDaoService;

import javassist.NotFoundException;
import lombok.var;

@Service
public class UserDaoServiceImpl  implements UserDaoService{

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserDaoMapper userDaoMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public boolean existsByUsername(String name) {
		
		return userRepository.existsByUsername(name);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsByEmail(String email) {
		
		return userRepository.existsByEmail(email);
	}

	@Override
	@Transactional
	public UserDto save(UserDto dto) throws NotFoundException {
		var user=userDaoMapper.toEntity(dto);
		var registeredVar=userDaoMapper.toDto(userRepository.save(user));
		emailService.sendEmailWelcome(dto.getEmail(),
									"Welcome"+dto.getUsername()+",you are registered!!!"
									, "Welcome to Alkemy");
		return registeredVar;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDto> findAll() {
		List<UserDao>users=(List<UserDao>) userRepository.findAll();
		return userDaoMapper.toDto(users) ;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto findByID(Long id) throws NotFoundException {
		var userDao=userRepository.findById(id).orElseThrow(()->new NotFoundException("Not User with Id"));
		return userDaoMapper.toDto(userDao);
	}

	@Override
	@Transactional
	public UserDto update(UserDto dto) throws NotFoundException {
		if(!userRepository.existsById(dto.getId())) {
			throw new NotFoundException("Not Exists User with ID");
		}
		var userVar= userRepository.save(userDaoMapper.toEntity(dto));
		return userDaoMapper.toDto(userVar);
	}

	@Override
	@Transactional
	public void delete(Long id) throws NotFoundException {
		userRepository.deleteById(id);
		
	}

}
