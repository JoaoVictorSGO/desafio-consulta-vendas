package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.DateRange;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SellerSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public Page<SaleReportDTO> searchByReport(
			String minDate,
			String maxDate,
			String name,
			Pageable pageable){
		DateRange date = parseDateRange(minDate, maxDate);
		return repository.searchByReport(date.minDate(), date.maxDate(), name, pageable);
	}

	public List<SellerSummaryDTO> searchBySummary(String minDate, String maxDate) {
		DateRange date = parseDateRange(minDate, maxDate);
		return repository.searchBySummary(date.minDate(), date.maxDate());
	}
	
	private DateRange parseDateRange(String minDate, String maxDate) {
		LocalDate startDate = minDate.equals("") ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1L) : LocalDate.parse(minDate);
		LocalDate endDate = maxDate.equals("")? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) :LocalDate.parse(maxDate);
		return new DateRange(startDate, endDate);
	}
	
}
