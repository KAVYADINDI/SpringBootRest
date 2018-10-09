package com.customerapp.customerapp.service;

import java.util.List;

import com.customerapp.customerapp.entity.Customer;
import com.customerapp.customerapp.exceptions.AuthenticationFailedException;
import com.customerapp.customerapp.exceptions.CustomerNotFoundException;

public interface CustomerService {

    public Customer addCustomer(Customer customer);
	public Customer authenticate(Customer customer) throws CustomerNotFoundException, AuthenticationFailedException;
	public Customer updateCustomer(Customer customer);
	public Customer getCustomer(int customerId) throws CustomerNotFoundException ;
	public void deleteCustomer(int customerId);	
	public List<Customer> getAllCustomers();
}
