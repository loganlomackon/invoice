package com.rbc.invoice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.invoice.domain.Brand;
import com.rbc.invoice.domain.InvoiceItem;
import com.rbc.invoice.repository.BrandRepository;
import com.rbc.invoice.rest.dto.BrandPointDTO;

@Service
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private InvoiceItemService invoiceItemService;
	
	@Transactional
	public Brand getById(Long id) {
		return brandRepository.getOne(id);
	}
	@Transactional
	public List<Brand> getAll() {
		return brandRepository.findByDeletedFalse();
	}
	@Transactional
	public Brand save(Brand b) {
		return brandRepository.save(b);
	}
	
	public List<BrandPointDTO> getAllBrandPoints() {
		List<Brand> brands = getAll();
		List<BrandPointDTO> dtos = new ArrayList<BrandPointDTO>();
		for (Brand brand : brands) {
			List<InvoiceItem> items = invoiceItemService.getByBrand(brand);
			if (items.size() == 0) {
				continue;
			}
			
			BigDecimal point = BigDecimal.ZERO;
			for (InvoiceItem i : items) {
				point = point.add(i.getPoint());
			}
			BrandPointDTO dto = new BrandPointDTO();
			dto.setBrand_name(brand.getName());
			dto.setPoint(String.valueOf(point.intValue()));
			dtos.add(dto);
		}
		return dtos;
	}
	
}
