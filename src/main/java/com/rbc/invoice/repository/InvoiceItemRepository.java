package com.rbc.invoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.invoice.domain.Brand;
import com.rbc.invoice.domain.Invoice;
import com.rbc.invoice.domain.InvoiceItem;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

	List<InvoiceItem> findByDeletedFalse();
	List<InvoiceItem> findByDeletedFalseAndInvoice(Invoice invoice);
	List<InvoiceItem> findByDeletedFalseAndBrand(Brand brand);
	
}
