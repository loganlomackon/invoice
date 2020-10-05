package com.rbc.invoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.invoice.domain.Brand;
import com.rbc.invoice.domain.BrandCommodityLine;

public interface BrandCommodityLineRepository extends JpaRepository<BrandCommodityLine, Long> {

	List<BrandCommodityLine> findByDeletedFalseAndBrand(Brand brand);
	BrandCommodityLine findByDeletedFalseAndName(String name);
	
}
