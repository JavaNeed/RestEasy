package test.resteasy.series.spring.mvc.hibernate.service;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.resteasy.series.spring.mvc.hibernate.model.Customer;
import com.resteasy.series.spring.mvc.hibernate.service.ICustomerService;

import in.benchresources.cdm.customer.CustomerListType;
import in.benchresources.cdm.customer.CustomerType;

public class CustomerTest {
	static final String ROOT_URL = "http://localhost:8080/RestEasy-Spring-MVC-Hibernate/resteasy/";
	final String path = "http://localhost:8080/RestEasy-Spring-MVC-Hibernate/";
	ICustomerService proxy = null;

	@Before
	public void beforeClass(){
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
		proxy = target.proxy(ICustomerService.class);
	}

	@Test
	public void testGetallcustomer() throws Exception {
		CustomerListType customers = proxy.getAllCustomerInfo();
		List<CustomerType> customerTypes = customers.getCustomerType();
		
		for (CustomerType customerType : customerTypes) {
			System.out.println("----------------------------------------------------");
			System.out.println("Name       : "+customerType.getName());
			System.out.println("Age        : "+customerType.getAge());
			System.out.println("CustomerId : "+customerType.getCustomerId());
		}
	}


	// POST
	@Test
	public void testSaveCustomerWithNewAPI() throws Exception{
		CustomerType customerType = new CustomerType();
		customerType.setAge(22);
		customerType.setCustomerId(8);
		customerType.setName("Prashant Pote");

		String response = proxy.createOrSaveNewCustomerInfo(customerType);
		System.out.println("HTTP code: " + response);
	}

	// With latest dependecy
	@Test
	public void testCustomerByIdNewAPI() throws Exception{	
		CustomerType customerType = proxy.getCustomerInfo(1);
		System.out.println("----------------------------------------------------");
		System.out.println("Name       : "+customerType.getName());
		System.out.println("Age        : "+customerType.getAge());
		System.out.println("CustomerId : "+customerType.getCustomerId());
	}


	
	/***********************************************
	 * 
	 * This is the old way with RestEasy with 2.0.x
	 * 
	 ***********************************************/
	
	@Test
	public void testSaveCustomer() throws Exception{
		Customer customer = new Customer();
		customer.setAge(22);
		customer.setCustomerId(6);
		customer.setName("Savani");

		ClientRequest request = new ClientRequest(ROOT_URL+"customerservice/addcustomer");
		request.body(MediaType.APPLICATION_JSON, customer);
		ClientResponse<String> response = request.post(String.class);
		String statusXML = response.getEntity();
		Assert.assertNotNull(statusXML);
	}

	@Test
	public void testCustomerById() throws Exception{
		ClientRequest request = new ClientRequest(ROOT_URL+"customerservice/getcustomer/1");
		request.accept("application/json");

		//Obtaining the client response
		ClientResponse response = request.get(Customer.class);

		if(response.getResponseStatus().getStatusCode() != 200){
			throw new RuntimeException("Failed with HTTP error code : "+ response.getResponseStatus().getStatusCode());
		}

		System.out.println("--------------------------------------");
		Customer customer = (Customer) response.getEntity();
		System.out.println("STATUS     : "+response.getStatus());
		System.out.println("Name       : "+customer.getName());
		System.out.println("Age        : "+customer.getAge());
		System.out.println("CustomerId : "+customer.getCustomerId());
	}

	@Test
	public void testDeleteCustomer() throws Exception{
		ClientRequest request = new ClientRequest(ROOT_URL+"deletecustomer/6");
		ClientResponse<String> response = request.delete(String.class);
		String statusXML = response.getEntity();
		Assert.assertNotNull(statusXML);
	}
}
