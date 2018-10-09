package com.customerapp.customerapp.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.customerapp.customerapp.entity.Customer;
import com.customerapp.customerapp.exceptions.CustomerNotFoundException;
import com.customerapp.customerapp.repository.CustomerRepository;
import com.customerapp.customerapp.service.impl.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerServiceImpl).build();
	}

//	@Test
//	public void addCustomerTest() {
//		Customer Customer = new Customer(123,"Jace","jjjj","jace@gmail.com","navi mumbai",LocalDate.of(1996,12,24));
//		when(customerRepository.save(Customer)).thenReturn(Customer);
//		Customer addCustomer = customerServiceImpl.addCustomer(Customer);
//		assertEquals(123, addCustomer.getCustomerId());
//	}


//	@Test
//	public void findCustomerByIdTest() throws CustomerNotFoundException {
//
//		Customer customer = new Customer(123,"Jace","jjjj","jace@gmail.com","mumbai",LocalDate.of(1996,12,24));
//		when(customerRepository.findById(123)).thenReturn(Optional.of(customer));
//		Customer result = customerServiceImpl.getCustomer(123);
//		assertEquals(123, result.getCustomerId());
//		assertEquals("Jace", result.getCustomerName());
//		assertEquals("jjjj", result.getCustomerPassword());
//		assertEquals("jace@gmail.com", result.getCustomerEmail());
//		assertEquals("mumbai", result.getCustomerAddress());
//		//assertEquals("1996-12-24", result.getCustomerDateOfBirth());
//	}

	@Test
	public void deleteCustomerTest() {
		Customer customer = new Customer(123,"Jace","jjjj","jace@gmail.com","mumbai",LocalDate.of(1996,12,24));
		customerServiceImpl.deleteCustomer(customer.getCustomerId());
		verify(customerRepository,times(1)).deleteById(customer.getCustomerId());
	}

	@Test
	public void authenticateCustomerTest()
	{
		
	}
}
