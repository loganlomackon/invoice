package com.rbc.invoice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.invoice.domain.BrandCommodityLine;
import com.rbc.invoice.domain.Invoice;
import com.rbc.invoice.domain.InvoiceItem;
import com.rbc.invoice.repository.InvoiceRepository;
import com.rbc.invoice.util.DateUtil;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private InvoiceItemService itemService;
	@Autowired
	private BrandCommodityLineService brandCommodityLineService;
	
	@Transactional
	public List<Invoice> getLatest() {
		return invoiceRepository.findFirst5ByDeletedFalseOrderByIdDesc();
	}
	@Transactional
	public List<Invoice> getAll() {
		return invoiceRepository.findByDeletedFalse();
	}
	@Transactional
	public Invoice save(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}
	@Transactional
	public List<Invoice> save(List<Invoice> invoices) {
		return invoiceRepository.saveAll(invoices);
	}
	
	public void deleteAll() {
		List<InvoiceItem> items = itemService.getAll();
		for (InvoiceItem i : items) {
			i.setDeleted(true);
		}
		itemService.save(items);
		
		List<Invoice> invoices = getAll();
		for (Invoice i : invoices) {
			i.setDeleted(true);
		}
		save(invoices);
	}
	
	public void parse(String content) {
		
		String serialNo = content.substring(0, 10);
		System.out.println("serialNo:"+serialNo);
		
		String date = content.substring(10, 17);
		System.out.println("date:"+date);
		
		String rand = content.substring(17, 21);
		System.out.println("rand:"+rand);
		
		String amount = content.substring(21, 29);
		System.out.println("amount:"+amount);
		
		String total = content.substring(29, 37);
		System.out.println("total:"+total);
		
		String buyerNo = content.substring(37, 45);
		System.out.println("buyerNo:"+buyerNo);
		
		String seller = content.substring(45, 53);
		System.out.println("seller:"+seller);
		
		String contentArea = content.substring(87);
		System.out.println("contentArea:"+contentArea);
		
		String[] contents = contentArea.split(":");
		
		String number1 = contents[1];
		System.out.println("number1:"+number1);
		String number2 = contents[2];
		System.out.println("number2:"+number2);
		String charset = contents[3];
		System.out.println("charset:"+charset);
		
		Integer number = Integer.valueOf(number1);
		for (int i = 0; i < number; i++) {
			System.out.println("item"+i+":"+contents[i*3+4]+", no:"+contents[i*3+5]+", price:"+contents[i*3+6]);
		}
		
	}

	public void parseAndSaveInvoice(String content) {
		
		String serialNo = content.substring(0, 10);
		String date = content.substring(10, 17);
		String rand = content.substring(17, 21);
		String amountHex = content.substring(21, 29);
		String totalHex = content.substring(29, 37);
		String buyerNo = content.substring(37, 45);
		String sellerNo = content.substring(45, 53);
		
		Invoice invoice = new Invoice();
		invoice.setSerialNo(serialNo);
		invoice.setIssueDate(DateUtil.setDate(
				1911+Integer.valueOf(date.substring(0, 3)), 
				Integer.valueOf(date.substring(3, 5)), 
				Integer.valueOf(date.substring(5, 7))));
		invoice.setTotalAmount(BigDecimal.valueOf(Integer.parseInt(totalHex, 16)));
		invoice.setBuyerNo(buyerNo);
		invoice.setSellerNo(sellerNo);
		invoice = save(invoice);
		
		String contentArea = content.substring(87);
		System.out.println("contentArea:"+contentArea);
		String[] contents = contentArea.split(":");
		
		String number1 = contents[1];
		System.out.println("number1:"+number1);
		String number2 = contents[2];
		System.out.println("number2:"+number2);
		String charset = contents[3];
		System.out.println("charset:"+charset);
		
		List<InvoiceItem> items = new ArrayList<InvoiceItem>();
		Integer number = Integer.valueOf(number1);
		for (int i = 0; i < number; i++) {
			String itemName = contents[i*3+4];
			BigDecimal itemNo = new BigDecimal(contents[i*3+5]);
			BigDecimal itemPrice = new BigDecimal(contents[i*3+6]);
			
			InvoiceItem item = new InvoiceItem();
			item.setInvoice(invoice);
			item.setName(itemName);
			item.setNumber(itemNo);
			item.setPrice(itemPrice);
			//Point
			BrandCommodityLine line = brandCommodityLineService.getbyName(itemName);
			if (line != null) {
				item.setBrand(line.getBrand());
				item.setPoint(itemNo.multiply(itemPrice).divide(line.getPointRate(), 0, RoundingMode.FLOOR));
			}
			
			items.add(item);
		}
		items = itemService.save(items);
	}
	
}
