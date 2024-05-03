package com.graphQL.component;

import java.util.List;

public record User(int userID, String fullname, String address, String phone, int order) {
	
	private static List<User> users = List.of(
			new User(1, "Ashwani", "Delhi", "9999888800", 1),
			new User(2, "Ram", "Gurgaon", "8888999900", 2),
			new User(3, "Rohit", "Noida", "0000999988", 3)
			);
	
	public static User getUserByID(int userID) {
		
		return users.stream()
				.filter(u -> u.userID()==userID)
				.findFirst()
				.orElse(null);
	}
	
	public static List<User> getAllUser(){
		return users;
	}

}
