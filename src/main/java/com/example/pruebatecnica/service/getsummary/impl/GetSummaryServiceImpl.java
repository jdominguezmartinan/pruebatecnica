package com.example.pruebatecnica.service.getsummary.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.pruebatecnica.repository.OrderRepository;
import com.example.pruebatecnica.service.dto.OrdersSummary;
import com.example.pruebatecnica.service.dto.Summary;
import com.example.pruebatecnica.service.getsummary.GetSummaryService;

@Service
public class GetSummaryServiceImpl implements GetSummaryService {

	private static final Logger logger = LoggerFactory.getLogger(GetSummaryServiceImpl.class);

	private final OrderRepository repository;

	public GetSummaryServiceImpl(OrderRepository repository) {
		this.repository = repository;  
	}

	@Override
	public OrdersSummary obtenerResumen() {
		logger.info("GetSummaryServiceImpl - Obtener Resumen: Entrada metodo ");
		OrdersSummary summary = new OrdersSummary();
		
		List<Summary> summaryCountry = repository.findByCountry();
		List<Summary> summaryItemType = repository.findByItemType();
		List<Summary> summaryPriority = repository.findByPriority();
		List<Summary> summaryRegion = repository.findByRegion();
		List<Summary> summarySalesChannel = repository.findBySalesChannel();
		
		summary.setCountry(summaryCountry);
		summary.setItemType(summaryItemType);
		summary.setOrderPriority(summaryPriority);
		summary.setRegion(summaryRegion);
		summary.setSalesChannel(summarySalesChannel);
		
		logger.info("GetSummaryServiceImpl - Obtener Resumen: "+summary.toString());

		return summary;
	}

}
