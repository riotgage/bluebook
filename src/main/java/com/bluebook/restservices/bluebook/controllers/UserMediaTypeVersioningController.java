package com.bluebook.restservices.bluebook.controllers;

import com.bluebook.restservices.bluebook.dto.UserDtoV1;
import com.bluebook.restservices.bluebook.dto.UserDtov2;
import com.bluebook.restservices.bluebook.entities.User;
import com.bluebook.restservices.bluebook.exceptions.UserNotFoundException;
import com.bluebook.restservices.bluebook.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/version/media/users")
public class UserMediaTypeVersioningController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Media type based Version 1
	@GetMapping(value = "/{id}", produces = "application/json;version=1")
	public UserDtoV1 getUserById(@Valid @Min(1) @PathVariable("id")Long id) throws UserNotFoundException {
		
		Optional<User> userOptional = userService.getUserById(id);
		
		if(!userOptional.isPresent()){
			throw new UserNotFoundException("user not found");
		}
		
		User user = userOptional.get();
		
		UserDtoV1 userMMDto= modelMapper.map(user, UserDtoV1.class);
		
		return userMMDto;
		
	}
	
	// Media type based Version 2
	@GetMapping(value="/{id}" ,produces = "application/json;version=2")
	public UserDtov2 getUserByIdV2(@Valid @Min(1) @PathVariable("id")Long id) throws UserNotFoundException {
		
		Optional<User> userOptional = userService.getUserById(id);
		
		if(!userOptional.isPresent()){
			throw new UserNotFoundException("user not found");
		}
		
		User user = userOptional.get();
		
		UserDtov2 userMMDto= modelMapper.map(user, UserDtov2.class);
		
		return userMMDto;
	}
}
