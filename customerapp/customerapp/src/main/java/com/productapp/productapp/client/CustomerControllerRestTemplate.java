/*package com.productapp.productapp.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import com.customerapp.customerapp.entity.Customer;

public class CustomerControllerRestTemplate {

	private static final RestTemplate REST_TEMPLATE = new RestTemplate();
	private static final String baseUrl = "http://localhost:9090/";
	
	public static void main(String[] args) {
		
		//adding new customer
		String url = baseUrl + "customers";
		Customer customer = new Customer(123,"Jace","jjjj","jace@gmail.com","mumbai",LocalDate.of(1996, 12, 18));
		customer = addCustomer(url, customer);
		
		
	    //Updating customer 
		String url = baseUrl + "customers";
		Customer customer = new  Customer(123,"Jace","jjjj","jace@gmail.com","navi mumbai",LocalDate.of(1996, 12, 18));
		updateCustomer(url, customer);
		customer = findCustomerById(baseUrl + "products/123");
		
		
		//finding customer by id
		String url = baseUrl + "/customers/123";
		Customer customer = findCustomerById(url);
		System.out.println("Product from DB : " + customer);
		
		
		//deleteCustomer
		String url = baseUrl + "customers/123";		
		deleteCustomer(url);
		
		
		//returning all customers
		String url = baseUrl + "customers/all";		
		returnAllCustomers(url);	
	}


	private static ResponseEntity<Customer> returnAllCustomers(String url) {
		return REST_TEMPLATE.getForEntity(url,Customer.class);
	}

	private static void deleteCustomer(String url) {
		REST_TEMPLATE.delete(url);
	}

	private static Customer findCustomerById(String url) {
		return REST_TEMPLATE.getForObject(url, Customer.class);
	}

	private static Customer addCustomer(String url, Customer customer) {
		return REST_TEMPLATE.postForObject(url, customer, Customer.class);
	}
	
	public static void updateCustomer(String url, Customer customer) {
		 REST_TEMPLATE.put(url, customer);
	}
	
	
}
*/