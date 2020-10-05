package com.rbc.invoice.rest.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class InvoiceDTO implements Serializable {

	private String content1;
	private String content2;

	public InvoiceDTO() {
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}
	
}
