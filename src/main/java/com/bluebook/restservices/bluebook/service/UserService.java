package com.bluebook.restservices.bluebook.service;

import com.bluebook.restservices.bluebook.entities.User;
import com.bluebook.restservices.bluebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	
	// Autowired user repo
	
	@Autowired
	private UserRepository userRepository;
	
	// Get ALL users
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
	}
	
	// Create a user
	public User createUser(User user){
		
		return userRepository.save(user);
	}
	
	// get a user by ID
	public Optional<User> getUserById(Long id){
		Optional<User> user=userRepository.findById(id);
		return user;
	}
	
	// Update a user by ID
	public User updateUserById(User user, Long id){
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id){
		if(userRepository.findById(id).isPresent())
		    userRepository.deleteById(id);
	}
	
	public User findUserByUsername(String username){
		return userRepository.findByUserName(username);
	}
}
