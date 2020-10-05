package com.rbc.invoice.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_BRAND", uniqueConstraints={
		@UniqueConstraint(columnNames={"ID"})})
public class Brand extends AbstractObject implements Serializable {

	@Column(name="NAME")
	private String name;
	
	@OneToMany(mappedBy = "brand", cascade = CascadeType.REFRESH)
	private List<BrandCommodityLine> lines;
	
	@OneToMany(mappedBy = "brand", cascade = CascadeType.REFRESH)
	private List<InvoiceItem> invoiceItems;

	public Brand() {
		this.setDeleted(false);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BrandCommodityLine> getLines() {
		return lines;
	}

	public void setLines(List<BrandCommodityLine> lines) {
		this.lines = lines;
	}

	public List<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

}
