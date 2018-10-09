package com.capgemini.orderapp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.capgemini.orderapp.controller.OrderController;
import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.service.OrderService;



@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderControllerTest {

	@Mock
	private OrderService orderService;

	@InjectMocks
	private OrderController orderController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}
	
	
	@Test
	public void testAddOrder() throws Exception {
	new Order(121,123,111,LocalDate.of(2018, 12, 03));
		when(orderService.addOrder(Mockito.isA(Order.class))).thenReturn(new Order(121,123,111,LocalDate.of(2018, 12, 03)));
		String content = "{}";
		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(content)
				.accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.orderId").exists())
				.andExpect(jsonPath("$.customerId").exists())
				.andExpect(jsonPath("$.products").exists())
				.andExpect(jsonPath("$.date").exists())
		        
		        .andExpect(jsonPath("$.orderId").value(121))
		    	.andExpect(jsonPath("$.customerId").value(123))
				.andExpect(jsonPath("$.products").value(111))
				//.andExpect(jsonPath("$.date",is(LocalDate.of(2018,12,03))))
		        .andDo(print());
	}

		@Test
	public void testFindOrderById() throws Exception {

		when(orderService.getOrderById(121)).thenReturn(new Order(121,123,111,LocalDate.of(2018, 12, 03)));

		mockMvc.perform(MockMvcRequestBuilders.get("/order/121").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.orderId").exists())
				.andExpect(jsonPath("$.customerId").exists())
				.andExpect(jsonPath("$.products").exists())
				.andExpect(jsonPath("$.date").exists())
		        
		        .andExpect(jsonPath("$.orderId").value(121))
		    	.andExpect(jsonPath("$.customerId").value(123))
				.andExpect(jsonPath("$.products").value(111))
				//.andExpect(jsonPath("$.date",is(LocalDate.of(2018,12,03))))
		        .andDo(print());
	}

		@Test
		public void testDeleteCustomer() throws Exception {
			mockMvc.perform(delete("/order/121").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		}
}
