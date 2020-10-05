package com.rbc.invoice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.invoice.domain.Invoice;
import com.rbc.invoice.rest.dto.Content2DTO;
import com.rbc.invoice.service.InvoiceService;


@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> parseInvoice(@RequestBody Content2DTO inputDTO) {
		try {
			String content1 = "AB112233441020523999900000276000002760000000001234567ydXZt4LAN1UHN/j 1juVcRA==:**********:4:4:1:乾電池:1:105:";
			String content2 = "**口罩:1:210:牛奶:1:25:黑松沙士:10:29";
			
			String content = content1+content2.substring(2);
			invoiceService.parseAndSaveInvoice(content);
			
			return ResponseEntity.status(HttpStatus.OK).body("ok");
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
	
	@RequestMapping(value="/latest", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getLatest() {
		try {
			List<Invoice> invoices = invoiceService.getLatest();
			
			return ResponseEntity.status(HttpStatus.OK).body("ok");
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteAll(@RequestBody Content2DTO inputDTO) {
		try {
			invoiceService.deleteAll();
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
}
