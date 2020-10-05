package com.rbc.invoice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.invoice.domain.Brand;
import com.rbc.invoice.domain.Invoice;
import com.rbc.invoice.domain.InvoiceItem;
import com.rbc.invoice.repository.InvoiceItemRepository;

@Service
public class InvoiceItemService {

	@Autowired
	private InvoiceItemRepository itemRepository;
	
	@Transactional
	public List<InvoiceItem> getAll() {
		return itemRepository.findByDeletedFalse();
	}
	@Transactional
	public List<InvoiceItem> getByInvoice(Invoice invoice) {
		return itemRepository.findByDeletedFalseAndInvoice(invoice);
	}
	@Transactional
	public List<InvoiceItem> getByBrand(Brand brand) {
		return itemRepository.findByDeletedFalseAndBrand(brand);
	}
	@Transactional
	public InvoiceItem save(InvoiceItem item) {
		return itemRepository.save(item);
	}
	@Transactional
	public List<InvoiceItem> save(List<InvoiceItem> items) {
		return itemRepository.saveAll(items);
	}
}
