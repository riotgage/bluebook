package com.bluebook.restservices.bluebook.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	// Method which will be mapped to URI
	// GET Method and its path (URI)
	
	@RequestMapping(method=RequestMethod.GET,path="/")
	public String test(){
		return "test";
	}
//	@RequestMapping(method=RequestMethod.GET,path="/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld(){
		return "Hello World";
	}
	
	@GetMapping("/helloworldbean")
	public UserDetails helloWorldBean(){
		return new UserDetails("Rohit","Satadhar","Pune");
	}
	
}
