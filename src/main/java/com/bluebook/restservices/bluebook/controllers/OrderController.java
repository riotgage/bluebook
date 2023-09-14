package com.bluebook.restservices.bluebook.controllers;

import com.bluebook.restservices.bluebook.entities.Order;
import com.bluebook.restservices.bluebook.entities.User;
import com.bluebook.restservices.bluebook.exceptions.OrderNotFoundException;
import com.bluebook.restservices.bluebook.exceptions.UserNotFoundException;
import com.bluebook.restservices.bluebook.repository.OrderRepository;
import com.bluebook.restservices.bluebook.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/users")
@Validated
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	// get All orders for a user
	@ApiOperation(value = "To access orders of a particular user")
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		
		Optional<User> user=userRepository.findById(userid);
		
		if(!user.isPresent()){
			throw new UserNotFoundException("User Not found");
		}
		
		return user.get().getOrder();
	}
	
	// create order
	@PostMapping("/{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		
		Optional<User> user=userRepository.findById(userid);
		
		if(!user.isPresent()){
			throw new UserNotFoundException("User Not found");
		}
		order.setUser(user.get());
		return orderRepository.save(order);
	}
	
	// get order by order id
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
