package com.capgemini.orderapp.service;

import java.util.List;


import com.capgemini.orderapp.entity.Order;



public interface OrderService  {

	public Order addOrder(Order order);

	public void cancelOrder(Order order);

	public Order getOrderById(int orderId);

	public List<Order> getAllOrders();

//	public List<Order> getOrdersByCustomerId(int customerId);
}
