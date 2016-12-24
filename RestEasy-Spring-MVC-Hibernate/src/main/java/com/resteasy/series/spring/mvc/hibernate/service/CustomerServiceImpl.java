package com.resteasy.series.spring.mvc.hibernate.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.resteasy.series.spring.mvc.hibernate.model.Customer;
import com.resteasy.series.spring.mvc.hibernate.repository.CustomerRepository;

import in.benchresources.cdm.customer.CustomerListType;
import in.benchresources.cdm.customer.CustomerType;

@Controller
public class CustomerServiceImpl implements ICustomerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public String createOrSaveNewCustomerInfo(CustomerType customerType) {

		Customer newCustomer = new Customer();
		newCustomer.setCustomerId(customerType.getCustomerId());
		newCustomer.setName(customerType.getName());
		newCustomer.setAge(customerType.getAge());
		return customerRepository.save(newCustomer).toString();
	}

	@Override
	public CustomerType getCustomerInfo(int customerId) {

		Customer getCustomer = customerRepository.findByCustomerId(customerId);

		CustomerType customerType = new CustomerType();
		customerType.setCustomerId(getCustomer.getCustomerId());
		customerType.setName(getCustomer.getName());
		customerType.setAge(getCustomer.getAge());
		return customerType;
	}

	@Override
	public CustomerListType getAllCustomerInfo() {

		List<Customer> lstCustomer = customerRepository.findAll();
		CustomerListType customerListType = new CustomerListType();

		for(Customer customer : lstCustomer){
			CustomerType customerType = new CustomerType();
			customerType.setCustomerId(customer.getCustomerId());
			customerType.setName(customer.getName());
			customerType.setAge(customer.getAge());
			customerListType.getCustomerType().add(customerType);
		}
		return customerListType;
	}

	@Override
	public ModelAndView viewAllCustomer() {

		CustomerListType customerListType = getAllCustomerInfo();
		return new ModelAndView("viewAllCustomer", "customerListType", customerListType);
	}

	@Override
	public void deleteCustomer(int customerId) {
		LOGGER.debug("Customer Id : {} deleted", customerId);
		Customer customer = customerRepository.findByCustomerId(customerId);
		customerRepository.delete(customer);
		
	}
}