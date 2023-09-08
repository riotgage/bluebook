package com.bluebook.restservices.bluebook.controllers;

import com.bluebook.restservices.bluebook.dto.UserMsDTO;
import com.bluebook.restservices.bluebook.entities.User;
import com.bluebook.restservices.bluebook.exceptions.UserNotFoundException;
import com.bluebook.restservices.bluebook.mappers.UserMapper;
import com.bluebook.restservices.bluebook.repository.UserRepository;
import com.bluebook.restservices.bluebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<UserMsDTO> getAllUserDtos(){
		return UserMapper.INSTANCE.usersToUserDtos(userRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public UserMsDTO getUserById(@Valid @Min(1) @PathVariable("id")Long id) throws UserNotFoundException {
		
		Optional<User> user = userService.getUserById(id);
		
		if(!user.isPresent()){
			throw new UserNotFoundException("User not found");
		}
		return UserMapper.INSTANCE.usertoUserMsDTO(user.get());
		
	}
}
