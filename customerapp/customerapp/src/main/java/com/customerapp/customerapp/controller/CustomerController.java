package com.customerapp.customerapp.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customerapp.customerapp.entity.Customer;
import com.customerapp.customerapp.exceptions.CustomerNotFoundException;
import com.customerapp.customerapp.service.CustomerService;


@RestController
public class CustomerController {

	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customerService.addCustomer(customer),
				HttpStatus.OK);
		logger.info("Added new Customer :"+ customer);
		return responseEntity;
	}
	
	@PutMapping("/customers")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer Customer) {
		try {
			customerService.getCustomer((Customer.getCustomerId()));
			logger.info("Updated Customer with Id :"+ Customer.getCustomerId()+" to : "+Customer);
			return new ResponseEntity<Customer>(customerService.updateProfile(Customer), HttpStatus.OK);
		} catch (CustomerNotFoundException exception) {
			logger.warn("CUSTOMER NOT FOUND"+ exception.getMessage());
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable int customerId) {
		try {
			Customer CustomerFromDb = customerService.getCustomer(customerId);
			logger.info("ID of Customer to be found : "+customerId);
			return new ResponseEntity<Customer>(CustomerFromDb, HttpStatus.OK);
		} catch (CustomerNotFoundException exception) {
			logger.warn("CUSTOMER NOT FOUND"+ exception.getMessage());
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId) {
		try {
			Customer CustomerFromDb = customerService.getCustomer(customerId);
			if (CustomerFromDb != null) {
				customerService.deleteCustomer(customerId);
				logger.info("Deleted Customer by id: "+customerId);
				return new ResponseEntity<Customer>(HttpStatus.OK);
			}
		} catch (CustomerNotFoundException exception) {
			logger.warn("CUSTOMER NOT FOUND"+ exception.getMessage());
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/customers/all")
	public ResponseEntity<List<Customer>> findAllCustomers() {
		logger.info("Returning all customers");
	return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(),HttpStatus.OK);
	}
	
	@PostMapping("/customers/customer")
	public ResponseEntity<Customer> authenticateCustomer(@RequestBody Customer customer) {
		ResponseEntity<Customer> responseEntity;
		try {
			responseEntity = new ResponseEntity<Customer>(customerService.authenticate(customer),
					HttpStatus.OK);
			logger.info("Authenticate Customer :"+ customer);
			return responseEntity;
		} catch (CustomerNotFoundException e) {
			
			logger.warn("SOMETHING WENT WRONG"+ e.getMessage());
		}	
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
}
