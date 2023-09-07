package com.bluebook.restservices.bluebook.controllers;

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
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/{id}")
	public UserMMDto getUserById(@Valid @Min(1) @PathVariable("id")Long id) throws UserNotFoundException {
		
		Optional<User> userOptional = userService.getUserById(id);
		
		if(!userOptional.isPresent()){
			throw new UserNotFoundException("user not found");
		}
		
		User user = userOptional.get();
		
		UserMMDto userMMDto= modelMapper.map(user,UserMMDto.class);
		
		return userMMDto;
		
		
		
	}

}
