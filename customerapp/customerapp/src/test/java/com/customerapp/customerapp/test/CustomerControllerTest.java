package com.customerapp.customerapp.test;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.customerapp.customerapp.controller.CustomerController;
import com.customerapp.customerapp.entity.Customer;
import com.customerapp.customerapp.exceptions.CustomerNotFoundException;
import com.customerapp.customerapp.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerControllerTest {

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

//	@Test
//	public void addCustomerTest() throws Exception {
//
//		String content = "{\r\n" + 
//				"	\"customerId\" : 123,\r\n" + 
//				"	\"customerName\" : \"Jace\",\r\n" + 
//				"	\"customerPassword\" : \"jjjj\",\r\n" + 
//				"	\"customerEmail\" : \"jace@gmail.com\",\r\n" + 
//				"	\"customerAddress\" : \"mumbai\",\r\n" + 
//				"	\"customerDateOfBirth\" : \"1996-12-24\"\r\n" + 
//				"}";
//
//		when(customerService.addCustomer(Mockito.isA(Customer.class)))
//				.thenReturn(new Customer(123,"Jace","jjjj","jace@gmail.com","mumbai",LocalDate.of(1996,12,24)));
//		mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content(content)
//				.accept(MediaType.APPLICATION_JSON))
//		        .andExpect(status().isOk())
//				.andExpect(jsonPath("$.customerId").exists())
//				.andExpect(jsonPath("$.customerName").exists())
//				.andExpect(jsonPath("$.customerPassword").exists())
//				.andExpect(jsonPath("$.customerEmail").exists())
//				.andExpect(jsonPath("$.customerAddress").exists())
//				.andExpect(jsonPath("$.customerDateOfBirth").exists())
//				
//				.andExpect(jsonPath("$.customerId").value(123))
//				.andExpect(jsonPath("$.customerName").value("Jace"))
//				.andExpect(jsonPath("customerPassword").value("jjjj"))
//				.andExpect(jsonPath("$.customerEmail").value("jace@gmail.com"))
//				.andExpect(jsonPath("$.customerAddress").value("mumbai"))
//				//.andExpect(jsonPath("$.customerDateOfBirth").value("1996-12-24"))
//				
//				.andDo(print());
//	}
//
//	@Test
//	public void updateCustomerTest() throws Exception {
//		String content = "{\"CustomerId\" : 123}"; //only id is what that matters here.
//
//		when(customerService.updateProfile(Mockito.isA(Customer.class)))
//				.thenReturn(new Customer(123,"Jace","jjjj","jace@gmail.com","mumbai",LocalDate.of(1996,12,24)));
//		
//		mockMvc.perform(put("/customers").contentType(MediaType.APPLICATION_JSON).content(content)
//				.accept(MediaType.APPLICATION_JSON)).
//		andExpect(status().isOk())
//		.andExpect(jsonPath("$.customerId").exists())
//		.andExpect(jsonPath("$.customerName").exists())
//		.andExpect(jsonPath("$.customerPassword").exists())
//		.andExpect(jsonPath("$.customerEmail").exists())
//		.andExpect(jsonPath("$.customerAddress").exists())
//		.andExpect(jsonPath("$.customerDateOfBirth").exists())
//		
//		.andExpect(jsonPath("$.customerId").value(123))
//		.andExpect(jsonPath("$.customerName").value("Jace"))
//		.andExpect(jsonPath("customerPassword").value("jjjj"))
//		.andExpect(jsonPath("$.customerEmail").value("jace@gmail.com"))
//		.andExpect(jsonPath("$.customerAddress").value("mumbai"))
//		
//		.andDo(print());
//	}
//
//	@Test
//	public void testFindCustomerById() throws Exception {
//
//		when(customerService.getCustomer(123)).thenReturn(new Customer(123,"Jace","jjjj","jace@gmail.com","mumbai",LocalDate.of(1996,12,24)));
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/customers/123").accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andExpect(jsonPath("$.customerId").exists())
//				.andExpect(jsonPath("$.customerName").exists())
//				.andExpect(jsonPath("$.customerPassword").exists())
//				.andExpect(jsonPath("$.customerEmail").exists())
//				.andExpect(jsonPath("$.customerAddress").exists())
//				.andExpect(jsonPath("$.customerDateOfBirth").exists())
//				
//				.andExpect(jsonPath("$.customerId").value(123))
//				.andExpect(jsonPath("$.customerName").value("Jace"))
//				.andExpect(jsonPath("customerPassword").value("jjjj"))
//				.andExpect(jsonPath("$.customerEmail").value("jace@gmail.com"))
//				.andExpect(jsonPath("$.customerAddress").value("mumbai"))
//				.andDo(print());
//	}
//
//	@Test
//	public void testDeleteCustomer() throws Exception {
//		when(customerService.getCustomer(123)).thenReturn(new Customer(123,"Jace","jjjj","jace@gmail.com","mumbai",LocalDate.of(1996,12,24)));
//		mockMvc.perform(delete("/customers/123").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
//	}
	
@Test 
public void testAuthenticateCustomer() throws Exception
{
	String content = "{\r\n" + 
			"	\"customerId\" : 123,\r\n" + 
			"	\"customerName\" : \"Jace\",\r\n" + 
			"	\"customerPassword\" : \"jjjj\",\r\n" + 
			"	\"customerEmail\" : \"jace@gmail.com\",\r\n" + 
			"	\"customerAddress\" : \"mumbai\",\r\n" + 
			"	\"customerDateOfBirth\" : \"1996-12-24\"\r\n" + 
			"}";
	
when(customerService.updateProfile(Mockito.isA(Customer.class)))
.thenReturn(new Customer(123,"Jace","jjjj","jace@gmail.com","mumbai",LocalDate.of(1996,12,24)));
	mockMvc.perform(put("/customers").contentType(MediaType.APPLICATION_JSON).content(content)
			.accept(MediaType.APPLICATION_JSON))
	.andExpect(status().isOk()).andExpect(jsonPath("$.customerId").exists())
	.andExpect(jsonPath("$.customerName").exists())
	.andExpect(jsonPath("$.customerPassword").exists())
	.andExpect(jsonPath("$.customerEmail").exists())
	.andExpect(jsonPath("$.customerAddress").exists())
	.andExpect(jsonPath("$.customerDateOfBirth").exists())
	
	.andExpect(jsonPath("$.customerId").value(123))
	.andExpect(jsonPath("$.customerName").value("Jace"))
	.andExpect(jsonPath("customerPassword").value("jjjj"))
	.andExpect(jsonPath("$.customerEmail").value("jace@gmail.com"))
	.andExpect(jsonPath("$.customerAddress").value("mumbai"))
	.andDo(print());
}
}
