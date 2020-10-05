package com.rbc.invoice.rest.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BrandPointDTO implements Serializable {

	private String brand_name;
	private String point;

	public BrandPointDTO() {
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
}
