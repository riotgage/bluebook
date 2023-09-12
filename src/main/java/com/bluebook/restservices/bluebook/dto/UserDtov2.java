package com.bluebook.restservices.bluebook.dto;

public class UserDtov2 {
	
	private Long id;
	private String userName;
	private String address;
	public UserDtov2() {
	}
	
	public UserDtov2(Long id, String userName, String address) {
		this.id = id;
		this.userName = userName;
		this.address = address;
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
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}
