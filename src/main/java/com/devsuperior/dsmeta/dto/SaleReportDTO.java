package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.projections.SaleProjection;

public class SaleReportDTO {
	private Long id;
	private LocalDate date;
	private Double amount;
	private String name;
	
	public SaleReportDTO() {
	}
	
	public SaleReportDTO(Long id, LocalDate date, Double amount, String name) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.name = name;
	}
	
	public SaleReportDTO(SaleProjection report) {
		id = report.getId();
		date = report.getDate();
		amount = report.getAmount();
		name = report.getName();
		
	}

	public Long getId() {
		return id;
	}
	public LocalDate getDate() {
		return date;
	}
	public Double getAmount() {
		return amount;
	}
	public String getName() {
		return name;
	}
	
	
}
