package com.bluebook.restservices.bluebook.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "FIRST_NAME",nullable = false,  length = 50)
	private String firstName;
	
	@Column(name = "LAST_NAME",nullable = false, length = 50)
	private String lastName;
	
	@Column(name = "USER_NAME",nullable = false, unique = true, length = 50)
	private String userName;
	
	@Column(name = "EMAIL",nullable = false,length = 50)
	private String email;
	
	@Column(name = "ROLE",nullable = false,length = 50)
	private String role;
	
	@Column(name = "SSN",nullable = false, unique = true, length = 50)
	private String ssn;
	
	public User() {
	}
	
	public User(Long id, String firstName, String lastName, String userName, String email, String role, String ssn) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getSsn() {
		return ssn;
	}
	
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
}