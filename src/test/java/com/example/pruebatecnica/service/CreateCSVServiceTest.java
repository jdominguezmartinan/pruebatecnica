package com.example.pruebatecnica.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.pruebatecnica.model.OrderEntity;
import com.example.pruebatecnica.repository.OrderRepository;
import com.example.pruebatecnica.repository.impl.OrderRepositoryImpl;
import com.example.pruebatecnica.service.createcsv.CreateCSVService;
import com.example.pruebatecnica.service.createcsv.impl.CreateCSVServiceImpl;

public class CreateCSVServiceTest {
	
	private JdbcTemplate jdbcTemplate;
	private OrderRepository orderRepository;
	private CreateCSVService createCsvService ;
     
    @BeforeEach
    public void setUp() {
    	CreateCSVServiceTest createTest = new CreateCSVServiceTest();
    	createTest.jdbcTemplate = mock(JdbcTemplate.class); 
    	orderRepository = new OrderRepositoryImpl(createTest.jdbcTemplate);
    	createCsvService = new CreateCSVServiceImpl(orderRepository);
    }
	@Test
	public void createCSV() throws Exception {
		List<OrderEntity> listOrder = new ArrayList<OrderEntity>();
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setUuid("123123123434345223");
	        orderEntity.setOrderid(new BigDecimal(1));
	        orderEntity.setRegion("Europe");
	        orderEntity.setCountry("Spain");
	        orderEntity.setItemType("Smartphones");
	        orderEntity.setSalesChannel("offLine");
	        orderEntity.setPriority("M");
	        orderEntity.setOrderDate(new Date(12122020));
	        orderEntity.setShipDate(new Date(12122020));
	        orderEntity.setUnitsSold(new BigDecimal(1593));
	        orderEntity.setUnitCost(new BigDecimal(6.92));
	        orderEntity.setUnitPrice(new BigDecimal(9.33)); 
	        orderEntity.setTotalRevenue(new BigDecimal(1482.34));
	        orderEntity.setTotalCost(new BigDecimal(11023.56));
	        orderEntity.setTotalProfit(new BigDecimal(3839.13));
	        listOrder.add(orderEntity);
		
		when(orderRepository.findAllOrderByOrderId()).thenReturn(listOrder);
		File file = createCsvService.createCSV();
		Assertions.assertNotNull(file);
	}
}

