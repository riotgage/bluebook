package com.bluebook.restservices.bluebook.controllers;

import com.bluebook.restservices.bluebook.entities.User;
import com.bluebook.restservices.bluebook.exceptions.UserExistsException;
import com.bluebook.restservices.bluebook.exceptions.UserNameNotFoundException;
import com.bluebook.restservices.bluebook.exceptions.UserNotFoundException;
import com.bluebook.restservices.bluebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		
		return  userService.getAllUsers();
	}
	
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder){
		try {
			userService.createUser(user);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
			
		}
		catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@Valid @Min(1) @PathVariable("id")Long id) throws UserNotFoundException {
		
			return  userService.getUserById(id);
		
	}
	
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id,@RequestBody User user){
		try {
			return userService.updateUserById(user,id);
		}
		catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@DeleteMapping("/users/{id}")
	public void updateUserById(@PathVariable("id") Long id){
		 userService.deleteUserById(id);
	}
	
	// YOu have just thrown the exception here. So if this exception is not caught anywhere it will go as is
	// as response.
	// you can catch it here and send the Response Entity
	// or you can catch it in global exception handler.
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
		
		User user= userService.findUserByUsername(username);
		if(user==null){
			throw new UserNameNotFoundException(username+" user not found.");
		}
		return user;
	}
}
