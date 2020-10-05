package com.rbc.invoice.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.invoice.domain.Brand;
import com.rbc.invoice.domain.BrandCommodityLine;
import com.rbc.invoice.rest.dto.BrandCommodityLineDTO;
import com.rbc.invoice.rest.dto.BrandDTO;
import com.rbc.invoice.rest.dto.BrandPointDTO;
import com.rbc.invoice.service.BrandCommodityLineService;
import com.rbc.invoice.service.BrandService;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;
	@Autowired
	private BrandCommodityLineService lineService;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createBrand(@RequestBody BrandDTO inputDTO) {
		try {
			Brand brand = new Brand();
			brand.setName(inputDTO.getName());
			brand = brandService.save(brand);
			BrandDTO dto = BrandDTO.createDTO(brand);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getBrands() {
		try {
			List<Brand> brands = brandService.getAll();
			List<BrandDTO> dtos = new ArrayList<BrandDTO>();
			for (Brand b : brands) {
				dtos.add(BrandDTO.createDTO(b));
			}
			return ResponseEntity.status(HttpStatus.OK).body(dtos);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
	
	@RequestMapping(value="/points", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getBrandPoints() {
		try {
			List<BrandPointDTO> dtos = brandService.getAllBrandPoints();
			return ResponseEntity.status(HttpStatus.OK).body(dtos);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
	
	@RequestMapping(value="/{id}/line", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getBrandLines(@PathVariable Long id) {
		try {
			Brand brand = brandService.getById(id);
			List<BrandCommodityLineDTO> dtos = new ArrayList<BrandCommodityLineDTO>();
			for (BrandCommodityLine c : brand.getLines()) {
				dtos.add(BrandCommodityLineDTO.createDTO(c));
			}
			return ResponseEntity.status(HttpStatus.OK).body(dtos);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
	
	@RequestMapping(value="/{id}/line", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createBrandLine(@PathVariable Long id, @RequestBody BrandCommodityLineDTO inputDTO) {
		try {
			Brand brand = brandService.getById(id);
			BrandCommodityLine line = new BrandCommodityLine();
			line.setBrand(brand);
			line.setName(inputDTO.getName());
			line.setPointRate(new BigDecimal(inputDTO.getPoint_rate()));
			line = lineService.save(line);
					
			BrandCommodityLineDTO dto = BrandCommodityLineDTO.createDTO(line);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
	
}
