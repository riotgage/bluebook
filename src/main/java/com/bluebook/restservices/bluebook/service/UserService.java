package com.bluebook.restservices.bluebook.service;

import com.bluebook.restservices.bluebook.entities.User;
import com.bluebook.restservices.bluebook.exceptions.UserNotFoundException;
import com.bluebook.restservices.bluebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user=userRepository.findById(id);
		if(!user.isPresent()){
			throw new UserNotFoundException("User not found in user repository");
		}
		
		return user;
	}
	
	// Update a user by ID
	public User updateUserById(User user, Long id) throws UserNotFoundException {
		Optional<User> optionalUser=userRepository.findById(id);
		if(!optionalUser.isPresent()){
			throw new UserNotFoundException("User not found in user repository, provide correct user ID");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id){
		Optional<User> optionalUser=userRepository.findById(id);
		if(!optionalUser.isPresent()){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cannot delete user. Not found in user repository, provide correct user ID");
		}
		userRepository.deleteById(id);
	}
	
	public User findUserByUsername(String username){
		return userRepository.findByUserName(username);
	}
}
