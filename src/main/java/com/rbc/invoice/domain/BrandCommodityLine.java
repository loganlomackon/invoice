package com.rbc.invoice.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_BRAND_COMMODITY_LINE", uniqueConstraints={
		@UniqueConstraint(columnNames={"ID"})})
public class BrandCommodityLine extends AbstractObject implements Serializable {

	@Column(name="NAME")
	private String name;
	
	@Column(name="POINT_RATE", precision=12, scale=6)
	private BigDecimal pointRate;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "brand_id")
	private Brand brand;

	public BrandCommodityLine() {
		this.setDeleted(false);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPointRate() {
		return pointRate;
	}

	public void setPointRate(BigDecimal pointRate) {
		this.pointRate = pointRate;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

}
