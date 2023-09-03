package com.bluebook.restservices.bluebook.controllers;

import com.bluebook.restservices.bluebook.entities.User;
import com.bluebook.restservices.bluebook.exceptions.UserNotFoundException;
import com.bluebook.restservices.bluebook.service.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/jackson/users")
@Validated
public class UserMappingJacksonController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@Valid @Min(1) @PathVariable("id")Long id) throws UserNotFoundException {
		
		Optional<User> userOptional =userService.getUserById(id);
		User user=userOptional.get();
		
		Set<String> fields= new HashSet<String>();
		
		// here we are giving the fields we want manually
		fields.add("id");
		fields.add("ssn");
		fields.add("userName");
		FilterProvider filterProvider= new SimpleFilterProvider()
				.addFilter("userFilter", SimpleBeanPropertyFilter.
						filterOutAllExcept(fields));
		MappingJacksonValue mapper=new MappingJacksonValue(user);
		mapper.setFilters(filterProvider);
		return mapper;
		
	}
	
	// get params from request and filter them dynamically
	// return only request params
	@GetMapping("/params/{id}")
	public MappingJacksonValue getUserById2(@Valid @Min(1) @PathVariable("id")Long id, @RequestParam Set<String> fields) throws UserNotFoundException {

		Optional<User> userOptional =userService.getUserById(id);
		User user=userOptional.get();

		FilterProvider filterProvider= new SimpleFilterProvider()
				.addFilter("userFilter", SimpleBeanPropertyFilter.
						filterOutAllExcept(fields));
		MappingJacksonValue mapper=new MappingJacksonValue(user);
		mapper.setFilters(filterProvider);
		return mapper;

	}
	
	
}
