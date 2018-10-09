package com.capgemini.orderapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.service.OrderService;

@Controller
public class OrderController {

	static Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		logger.info("Add new order: " + order );
		return new ResponseEntity<Order>(orderService.addOrder(order), HttpStatus.OK);
	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> findOrderById(@PathVariable int orderId) {
		logger.info("Get order by Id: " + orderId);
		return new ResponseEntity<Order>(orderService.getOrderById(orderId), HttpStatus.OK);
	}

//	@GetMapping("/order/customer/{customerId}")
//	public ResponseEntity<List<Order>> findOrderByCustomerId(@PathVariable int customerId) {
//		logger.info("Get order by Id: " + customerId);
//		return new ResponseEntity<List<Order>>(orderService.getOrdersByCustomerId(customerId), HttpStatus.OK);
//	}

	@DeleteMapping("/order/{orderId}")
	public ResponseEntity<Order> deleteOrderById(@PathVariable int orderId) {
		logger.info("Delete order : " + orderId);
		Order order = orderService.getOrderById(orderId);
		System.out.println(order);
		orderService.cancelOrder(order);
		return new ResponseEntity<Order>(HttpStatus.OK);
	}

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> findAllOrder() {
		logger.info("Get All orders");
		return new ResponseEntity<List<Order>>(orderService.getAllOrders(), HttpStatus.OK);
	}
}
