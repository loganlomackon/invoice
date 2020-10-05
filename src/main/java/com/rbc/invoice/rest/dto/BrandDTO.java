package com.rbc.invoice.rest.dto;

import java.io.Serializable;

import com.rbc.invoice.domain.Brand;

@SuppressWarnings("serial")
public class BrandDTO implements Serializable {

	private String id;
	private String name;

	public BrandDTO() {
	}
	
	public static BrandDTO createDTO(Brand b) {
		BrandDTO dto = new BrandDTO();
		dto.setId(b.getId().toString());
		dto.setName(b.getName());
		return dto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
