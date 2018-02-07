package com.sp17.domain;



public class ServiceLevel {
	
	
	private long id;
	
	private String name;
	private double price;
	
	public ServiceLevel(){}

	public ServiceLevel(long id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ServiceLevel [id=" + id + ", Name=" + name + ", price=" + price + "]";
	}
	
	

}
