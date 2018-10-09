package com.capgemini.orderapp.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ordertable")
public class Order {
	
	@Id
	private int orderId;
	private int customerId;
	private int products;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	//private static int idValue=101;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int orderId, int customerId, int products, LocalDate date) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.products = products;
		this.date = date;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getProducts() {
		return products;
	}
	public void setProducts(int products) {
		this.products = products;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", products=" + products + ", date=" + date
				+ "]";
	}
	
}
