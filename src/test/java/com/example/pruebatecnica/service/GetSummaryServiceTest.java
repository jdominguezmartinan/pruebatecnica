package com.example.pruebatecnica.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.pruebatecnica.repository.OrderRepository;
import com.example.pruebatecnica.repository.impl.OrderRepositoryImpl;
import com.example.pruebatecnica.service.dto.OrdersSummary;
import com.example.pruebatecnica.service.dto.Summary;
import com.example.pruebatecnica.service.getsummary.GetSummaryService;
import com.example.pruebatecnica.service.getsummary.impl.GetSummaryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GetSummaryServiceTest {

	private JdbcTemplate jdbcTemplate;
	private OrderRepository orderRepository;
	private GetSummaryService getSummaryService;


	@BeforeEach
	public void setUp() {
		jdbcTemplate = mock(JdbcTemplate.class);  
    	orderRepository = new OrderRepositoryImpl(jdbcTemplate);
    	getSummaryService = new GetSummaryServiceImpl(orderRepository);	}

	@Test
	public void obtenerResumen() throws Exception {
		
		List<Summary> summaryList = new ArrayList<Summary>();
		Summary summary = new Summary();
		summary.setClave("1");
		summary.setValor("1");
		summaryList.add(summary); 
		 
		when(jdbcTemplate.query(any(String.class), any(BeanPropertyRowMapper.class))).thenReturn(summaryList);

		OrdersSummary resumen = getSummaryService.obtenerResumen();
		
		Assertions.assertEquals(resumen.getCountry(),summaryList);
		Assertions.assertEquals(resumen.getOrderPriority(),summaryList);
		Assertions.assertEquals(resumen.getRegion(),summaryList);
		Assertions.assertEquals(resumen.getSalesChannel(),summaryList);

	}

}
