package com.kafkastreams.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Product implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;
	private int pid;
	private String title;
	private String description;
	private double price;
	private double discountPercentage;
	private double rating;
	private double stock;
	private String brand;
	private String category;
	private LocalDateTime createdAt;
	
	public int getPid() {
		return pid;
	}
	public String getTitle() {
		return this.title;
	}
	public String getDescription() {
		return description;
	}
	public double getPrice() {
		return price;
	}
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public double getRating() {
		return rating;
	}
	public double getStock() {
		return stock;
	}
	public String getBrand() {
		return brand;
	}
	public String getCategory() {
		return category;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public void setProductTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public void setStock(double stock) {
		this.stock = stock;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
}
