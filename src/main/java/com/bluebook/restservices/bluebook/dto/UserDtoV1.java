package com.bluebook.restservices.bluebook.dto;

public class UserDtoV1 {
	
	private Long id;
	private String userName;
	private String email;
	
	public UserDtoV1() {
	}
	
	public UserDtoV1(Long id, String userName, String email) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
