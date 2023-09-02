package com.bluebook.restservices.bluebook.controllers;

import com.bluebook.restservices.bluebook.entities.Order;
import com.bluebook.restservices.bluebook.entities.User;
import com.bluebook.restservices.bluebook.exceptions.OrderNotFoundException;
import com.bluebook.restservices.bluebook.exceptions.UserNotFoundException;
import com.bluebook.restservices.bluebook.repository.UserRepository;
import com.bluebook.restservices.bluebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/{id}")
	public User getUserById(@Valid @Min(1) @PathVariable("id")Long id) throws UserNotFoundException {
		Optional<User> userOptional=  userService.getUserById(id);
		
		User user=userOptional.get();
		
		Long userid=user.getId();
		// we will use this id to create a self link
		
		Link selfLink= WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
		
		user.add(selfLink);
		
		return user;
	}
	
	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException, OrderNotFoundException {
		
		List<User> users=userService.getAllUsers();
		
		for(User user: users){
			
			Long userId=user.getId();
			
			Link selflink=WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selflink);
			
			// add relationship link with getAll orders
			
			CollectionModel<Order> order=WebMvcLinkBuilder.methodOn(OrderHateOasController.class)
					.getAllOrders(userId);
			
			Link ordersLink=WebMvcLinkBuilder.linkTo(order).withRel("all-orders");
			user.add(ordersLink);
			
			for(Order o:user.getOrder()){
				Order l=WebMvcLinkBuilder.methodOn(OrderHateOasController.class)
						.getOrderByorderId(userId,o.getOrderid());
				Link ordLnk=WebMvcLinkBuilder.linkTo(l).withSelfRel();
				o.add(ordLnk);
			}
		}
		Link selfLinkGetAllUsers= WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		CollectionModel<User> result = CollectionModel.of(users,selfLinkGetAllUsers);
		
		return result;
	}
	
	
}
