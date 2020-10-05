package com.rbc.invoice.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_INVOICE_ITEM", uniqueConstraints={
		@UniqueConstraint(columnNames={"ID"})})
public class InvoiceItem extends AbstractObject implements Serializable {

	@Column(name="NAME")
	private String name;
	
	@Column(name="NUMBER", precision=28, scale=6)
	private BigDecimal number;
	
	@Column(name="PRICE", precision=28, scale=6)
	private BigDecimal price;
	
	//Point
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@Column(name="POINT", precision=28, scale=6)
	private BigDecimal point;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;

	public InvoiceItem() {
		this.setDeleted(false);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getNumber() {
		return number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

}
