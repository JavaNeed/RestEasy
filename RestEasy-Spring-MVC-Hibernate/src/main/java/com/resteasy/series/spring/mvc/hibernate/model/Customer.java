package com.resteasy.series.spring.mvc.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	// member variables
	@Id 
	@GeneratedValue
	@Column(name = "CUSTOMER_ID")
	private int customerId;

	@Column(name= "NAME")
	private String name;

	@Column(name= "AGE")
	private int age;

	// default constructor
	public Customer() {
		super();
	}

	// 3-arg parameterized-constructor
	public Customer(int customerId, String name, int age) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.age = age;
	}

	// getters & setters
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}