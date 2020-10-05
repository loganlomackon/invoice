package com.rbc.invoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.invoice.domain.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	List<Invoice> findByDeletedFalse();
	List<Invoice> findFirst5ByDeletedFalseOrderByIdDesc();
	
}
