package com.resteasy.series.spring.mvc.hibernate.service;

import in.benchresources.cdm.customer.CustomerListType;
import in.benchresources.cdm.customer.CustomerType;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.servlet.ModelAndView;

@Path("/resteasy/customerservice")
public interface ICustomerService {

	// Basic CRUD operations for Customer Service

	// http://localhost:8080/RestEasy-Spring-MVC-Hibernate/resteasy/customerservice/addcustomer  - Tomcat 7.0.x
	@POST
	@Path("addcustomer")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_FORM_URLENCODED})
	public String createOrSaveNewCustomerInfo(CustomerType customerType);

	// http://localhost:8080/RestEasy-Spring-MVC-Hibernate/resteasy/customerservice/getcustomer/10001  - Tomcat 7.0.x
	@GET
	@Path("getcustomer/{id}")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public CustomerType getCustomerInfo(@PathParam("id") int customerId);

	// http://localhost:8080/RestEasy-Spring-MVC-Hibernate/resteasy/customerservice/getallcustomer  - Tomcat 7.0.x
	@GET
	@Path("getallcustomer")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public CustomerListType getAllCustomerInfo();

	// REST allows a client to select which format it prefers to receive the data in through a process called Content Negotiation. 
	// Content Negotiation can happen through HTTP headers, URI or query parameters.

	// http://localhost:8080/RestEasy-Spring-MVC-Hibernate/resteasy/customerservice/viewallcustomer  - Tomcat 7.0.x
	@GET
	@Path("viewallcustomer")
	@Produces(MediaType.TEXT_HTML)
	public ModelAndView viewAllCustomer();
	
	
	// http://localhost:8080/RestEasy-Spring-MVC-Hibernate/resteasy/deletecustomer/6
	@DELETE
	@Path("deletecustomer/{id}")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void deleteCustomer(@PathParam("id") int customerId);
}