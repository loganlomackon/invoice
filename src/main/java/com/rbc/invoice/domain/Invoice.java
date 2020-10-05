package com.rbc.invoice.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_INVOICE", uniqueConstraints={
		@UniqueConstraint(columnNames={"ID"})})
public class Invoice extends AbstractObject implements Serializable {

	@Column(name="SERIAL_NO")
	private String serialNo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ISSUE_DATE")
	private Date issueDate;
	
	@Column(name="TOTAL_AMOUNT", precision=28, scale=6)
	private BigDecimal totalAmount;
	
	@Column(name="BUYER_NO")
	private String buyerNo;
	
	@Column(name="SELLER_NO")
	private String sellerNo;
	
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.REFRESH)
	private List<InvoiceItem> items;

	public Invoice() {
		this.setDeleted(false);
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBuyerNo() {
		return buyerNo;
	}

	public void setBuyerNo(String buyerNo) {
		this.buyerNo = buyerNo;
	}

	public String getSellerNo() {
		return sellerNo;
	}

	public void setSellerNo(String sellerNo) {
		this.sellerNo = sellerNo;
	}

	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}

}
