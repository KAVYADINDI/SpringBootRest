package com.capgemini.orderapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.repository.OrderRepository;
import com.capgemini.orderapp.service.OrderService;
import com.capgemini.orderapp.service.exceptions.OrderExistsException;
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order addOrder(Order order) {
		Optional<Order> orderFromDb = orderRepository.findById(order.getOrderId());
		if (!orderFromDb.isPresent()) {
			return orderRepository.save(order);
		}
		throw new OrderExistsException("Order with id " +order.getOrderId()+" already exists");
	}

	@Override
	public void cancelOrder(Order order) {
		Optional<Order> orderFromDb = orderRepository.findById(order.getOrderId());
		if (orderFromDb.isPresent()) {
			orderRepository.delete(order);
			return;
		}
		throw new OrderDoesnotExistsException("Order with id " +order.getOrderId()+" does not exist");
	}

	@Override
	public Order getOrderById(int orderId) {
		Optional<Order> orderFromDb = orderRepository.findById(orderId);
		if (orderFromDb.isPresent()) {
			return orderFromDb.get();
		}
		throw new OrderDoesnotExistsException("Order with id " +orderId+" does not exist");
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

//	@Override
//	public List<Order> getOrdersByCustomerId(int customerId) {
//		return orderRepository.findOrdersByCustomerId(customerId);
//	}
}	

