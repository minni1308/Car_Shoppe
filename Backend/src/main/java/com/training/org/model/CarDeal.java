package com.training.org.model;


import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="cardeal", schema="test")
public class CarDeal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String brand_name;
	private String car_name;
	private double mileage;
	private double engine_displacement;
	private double seating_capacity;
	private String type;
	private String dealerName;
	private String image;
	private double price;
	
	@Lob
	@Column(name="car_image", length=1000)
	private byte[] carImage;

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public double getMileage() {
		return mileage;
	}
	public void setMileage(double mileage) {
		this.mileage = mileage;
	}
	public double getEngine_displacement() {
		return engine_displacement;
	}
	public void setEngine_displacement(double engine_displacement) {
		this.engine_displacement = engine_displacement;
	}
	public double getSeating_capacity() {
		return seating_capacity;
	}
	public void setSeating_capacity(double seating_capacity) {
		this.seating_capacity = seating_capacity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDealer_name() {
		return dealerName;
	}
	public void setDealer_name(String dealer_name) {
		this.dealerName = dealer_name;
	}
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public byte[] getCarImage() {
		return carImage;
	}
	public void setCarImage(byte[] carImage) {
		this.carImage = carImage;
	}
	public CarDeal() {}
	public CarDeal(String brand_name, String car_name, double mileage, double engine_displacement,
			double seating_capacity, String type, String dealerName, String image, double price, byte[] carImage) {
		super();
		this.brand_name = brand_name;
		this.car_name = car_name;
		this.mileage = mileage;
		this.engine_displacement = engine_displacement;
		this.seating_capacity = seating_capacity;
		this.type = type;
		this.dealerName = dealerName;
		this.image = image;
		this.price = price;
		this.carImage = carImage;
	}
	@Override
	public String toString() {
		return "CarDeal [id=" + id + ", brand_name=" + brand_name + ", car_name=" + car_name + ", mileage=" + mileage
				+ ", engine_displacement=" + engine_displacement + ", seating_capacity=" + seating_capacity + ", type="
				+ type + ", dealerName=" + dealerName + ", image=" + image + ", price=" + price + ", carImage="
				+ Arrays.toString(carImage) + "]";
	}
	
	
	
}