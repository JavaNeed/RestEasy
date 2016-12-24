package com.resteasy.series.spring.mvc.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resteasy.series.spring.mvc.hibernate.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Customer findByCustomerId(int customerId);
}
