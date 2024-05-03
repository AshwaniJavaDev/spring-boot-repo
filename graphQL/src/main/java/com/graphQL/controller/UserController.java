package com.graphQL.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.graphQL.component.Order;
import com.graphQL.component.User;
import com.graphQL.exception.UserGraphQLException;

@Controller
public class UserController {

	
	@QueryMapping
	public User getUserByID(@Argument int userID) {
		
		if(!Objects.isNull(User.getUserByID(userID))) {
			return User.getUserByID(userID);
		}else {
			throw new UserGraphQLException("User Not Found");
		}	
	}
	

	@SchemaMapping
	public Order order(User user) {
		return Order.getOrderById(user.order());
	}
	
	@QueryMapping
	public List<User> getAllUser() {
		return User.getAllUser();
	}
	
	
}
