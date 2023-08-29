package com.bluebook.restservices.bluebook.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue
	private Long orderid;
	
	private String orderDescription;
	
	// Multipel orders can be associated with one user
	// unless we use getter from this attribute user wont be fetched
	// order is owner side
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	public Order() {
	}
	
	public Long getOrderid() {
		return orderid;
	}
	
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
	public String getOrderDescription() {
		return orderDescription;
	}
	
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
