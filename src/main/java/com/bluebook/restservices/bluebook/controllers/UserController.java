package com.bluebook.restservices.bluebook.controllers;

import com.bluebook.restservices.bluebook.entities.User;
import com.bluebook.restservices.bluebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public User createUser(@RequestBody User user){
		return  userService.createUser(user);
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		return  userService.getUserById(id);
	}
	
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id,@RequestBody User user){
		return userService.updateUserById(user,id);
	}
	
	@DeleteMapping("/users/{id}")
	public void updateUserById(@PathVariable("id") Long id){
		 userService.deleteUserById(id);
	}
	
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username){
		
		return userService.findUserByUsername(username);
	}
}
