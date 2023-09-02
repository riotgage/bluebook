package com.bluebook.restservices.bluebook.controllers;

import com.bluebook.restservices.bluebook.entities.Order;
import com.bluebook.restservices.bluebook.entities.User;
import com.bluebook.restservices.bluebook.exceptions.OrderNotFoundException;
import com.bluebook.restservices.bluebook.exceptions.UserNotFoundException;
import com.bluebook.restservices.bluebook.repository.OrderRepository;
import com.bluebook.restservices.bluebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class OrderHateOasController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@GetMapping("/{userid}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		
		Optional<User> user=userRepository.findById(userid);
		
		if(!user.isPresent()){
			throw new UserNotFoundException("User Not found");
		}
		List<Order> allOrders= user.get().getOrder();
		Long userId=user.get().getId();
		for(Order order:allOrders){
			Long orderId=order.getOrderid();
			Link link= WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).slash("orders").slash(orderId).withSelfRel();
			order.add(link);
		}
		CollectionModel<Order> result= CollectionModel.of(allOrders);
		return result;
	}
	
	@GetMapping("/{userid}/orders/{orderId}")
	public Order getOrderByorderId(@PathVariable Long userid,@PathVariable Long orderId)
			throws UserNotFoundException, OrderNotFoundException {
		
		Optional<User> user=userRepository.findById(userid);
		
		if(!user.isPresent()){
			throw new UserNotFoundException("User Not found");
		}
		
		Optional<Order> order=orderRepository.findById(orderId);
		
		if(!order.isPresent()){
			throw new OrderNotFoundException("Order Not found");
		}
		
		return order.get();
	}
}
