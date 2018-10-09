package com.capgemini.orderapp;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.repository.OrderRepository;
import com.capgemini.orderapp.service.impl.OrderServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class orderServiceTerst {

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderServiceImpl).build();
	}

	@Test
	public void addCustomerTest() {
		Order order = new Order(121,123,111,LocalDate.of(2018, 12, 03));
		when(orderRepository.save(order)).thenReturn(order);
		Order result = orderServiceImpl.addOrder(order);
		assertEquals(121, result.getOrderId());
		assertEquals(123, result.getCustomerId());
		assertEquals(111, result.getProducts());
		assertEquals("2018-12-03", result.getDate().toString());
	}


	@Test
	public void findCustomerByIdTest()  {

		Order order = new Order(121,123,111,LocalDate.of(2018, 12, 03));
		when(orderRepository.findById(121)).thenReturn(Optional.of(order));
		Order result = orderServiceImpl.getOrderById(121);
		assertEquals(121, result.getOrderId());
		assertEquals(123, result.getCustomerId());
		assertEquals(111, result.getProducts());
		assertEquals("2018-12-03", result.getDate().toString());
	}

	@Test
	public void deleteCustomerTest() {
		Order order = new Order(121,123,111,LocalDate.of(2018, 12, 03));
		orderServiceImpl.cancelOrder(order);
		verify(orderRepository,times(1)).deleteById(order.getOrderId());
	}
}
