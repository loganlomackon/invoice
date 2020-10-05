package com.rbc.invoice.rest.dto;

import java.io.Serializable;

import com.rbc.invoice.domain.BrandCommodityLine;

@SuppressWarnings("serial")
public class BrandCommodityLineDTO implements Serializable {

	private String name;
	private String point_rate;

	public BrandCommodityLineDTO() {
	}
	
	public static BrandCommodityLineDTO createDTO(BrandCommodityLine c) {
		BrandCommodityLineDTO dto = new BrandCommodityLineDTO();
		dto.setName(c.getName());
		dto.setPoint_rate(c.getPointRate().toPlainString());
		return dto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoint_rate() {
		return point_rate;
	}

	public void setPoint_rate(String point_rate) {
		this.point_rate = point_rate;
	}
	
}
