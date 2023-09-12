package com.bluebook.restservices.bluebook.controllers;

import com.bluebook.restservices.bluebook.dto.UserDtoV1;
import com.bluebook.restservices.bluebook.dto.UserDtov2;
import com.bluebook.restservices.bluebook.dto.UserMMDto;
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
@RequestMapping("/version/uri/users")
public class UserUriVersioningController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// URI based Version 1
	@GetMapping({ "/v1.0/{id}", "/v1.1/{id}" })
	public UserDtoV1 getUserById(@Valid @Min(1) @PathVariable("id")Long id) throws UserNotFoundException {
		
		Optional<User> userOptional = userService.getUserById(id);
		
		if(!userOptional.isPresent()){
			throw new UserNotFoundException("user not found");
		}
		
		User user = userOptional.get();
		
		UserDtoV1 userMMDto= modelMapper.map(user, UserDtoV1.class);
		
		return userMMDto;
		
	}
	
	@GetMapping({ "/v2.0/{id}", "/v2.1/{id}" })
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
