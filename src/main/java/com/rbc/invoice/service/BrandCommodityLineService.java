package com.rbc.invoice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.invoice.domain.Brand;
import com.rbc.invoice.domain.BrandCommodityLine;
import com.rbc.invoice.repository.BrandCommodityLineRepository;

@Service
public class BrandCommodityLineService {

	@Autowired
	private BrandCommodityLineRepository lineRepository;
	
	@Transactional
	public List<BrandCommodityLine> getByBrand(Brand brand) {
		return lineRepository.findByDeletedFalseAndBrand(brand);
	}
	@Transactional
	public BrandCommodityLine getbyName(String name) {
		return lineRepository.findByDeletedFalseAndName(name);
	}
	@Transactional
	public BrandCommodityLine save(BrandCommodityLine l) {
		return lineRepository.save(l);
	}
	
}
