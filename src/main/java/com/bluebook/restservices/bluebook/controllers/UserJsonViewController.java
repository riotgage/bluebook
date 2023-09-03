package com.bluebook.restservices.bluebook.controllers;

import com.bluebook.restservices.bluebook.entities.User;
import com.bluebook.restservices.bluebook.entities.Views;
import com.bluebook.restservices.bluebook.exceptions.UserNotFoundException;
import com.bluebook.restservices.bluebook.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value="/jsonview/users")
public class UserJsonViewController {
	
	
	@Autowired
	UserService userService;
	
	@JsonView(Views.External.class)
	@GetMapping("/external/{id}")
	public Optional<User> getUserByIdExternal(@Valid @Min(1) @PathVariable("id")Long id) throws UserNotFoundException {
		
		return  userService.getUserById(id);
		
	}
	
	@JsonView(Views.Internal.class)
	@GetMapping("/internal/{id}")
	public Optional<User> getUserByIdInternal(@Valid @Min(1) @PathVariable("id")Long id) throws UserNotFoundException {
		
		return  userService.getUserById(id);
		
	}

}
