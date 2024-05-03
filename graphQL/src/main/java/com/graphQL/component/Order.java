package com.graphQL.component;

import java.time.LocalDate;
import java.util.List;


public record Order(int orderID, String orderDetails, LocalDate date, int price) {
	
	public Order{
	}

	private static List<Order> orders = List.of(
			new Order(1, "Voltas AC", LocalDate.now(), 22000),
			new Order(2, "Vivo v20", LocalDate.now(), 22000),
			new Order(3,"JBL Headphone", LocalDate.now(), 22000),
			new Order(4, "Watch", LocalDate.now(), 22000)
			);
	
	public static Order getOrderById(int orderId) {
		
		return orders.stream()
				.filter(o -> o.orderID()==orderId)
				.findFirst().orElse(null);
	}
}
