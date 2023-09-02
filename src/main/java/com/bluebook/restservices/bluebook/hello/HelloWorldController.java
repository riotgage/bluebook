package com.bluebook.restservices.bluebook.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

	// Method which will be mapped to URI
	// GET Method and its path (URI)
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
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
	
	@GetMapping("/hello-int")
	public String getMessagesInI18nFormat(@RequestHeader(name="Accept-Language",required = false)String locale){
		return messageSource.getMessage("label.hello",null,new Locale(locale));
		
		//		LocaleContextHolder.getLocale(); this can be used to get locale directly instead
		//		of writing (@RequestHeader(name="Accept-Language",required = false) in method signature
	}
}
