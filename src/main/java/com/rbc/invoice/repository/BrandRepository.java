package com.rbc.invoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.invoice.domain.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	List<Brand> findByDeletedFalse();
	
}
