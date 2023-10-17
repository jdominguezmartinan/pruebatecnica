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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import com.example.pruebatecnica.repository.impl.OrderRepositoryImpl;
import com.example.pruebatecnica.service.dto.Link;
import com.example.pruebatecnica.service.dto.Order;
import com.example.pruebatecnica.service.dto.PageOrder;
import com.example.pruebatecnica.service.saveorders.SaveOrdersService;
import com.example.pruebatecnica.service.saveorders.impl.SaveOrdersServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SaveOrdersServiceTest {

	private JdbcTemplate jdbcTemplate;
	private OrderRepositoryImpl orderRepository;
	private SaveOrdersService saveOrdersService;


	@BeforeEach
	public void setUp() {
		jdbcTemplate = mock(JdbcTemplate.class); 
    	orderRepository = new OrderRepositoryImpl(jdbcTemplate);
    	saveOrdersService = new SaveOrdersServiceImpl(orderRepository);	}

	@Test
	public void guardarPedidos() throws Exception {
		
		PageOrder pageOrder = new PageOrder();
		Link link = new Link();
		link.setNext("https://kata-espublicotech.g3stiona.com:443/v1/orders?page=2&max-per-page=1000");

		List<Order> content = new ArrayList<Order>();
		Order order = new Order();
		order.setUuid("sdfsdfsdfsdfsdfsdfsd");
		order.setId(1);
		order.setRegion("Europe");
		order.setCountry("Spain");
		order.setItem_type("Cars");
		order.setSales_channel("offLine");
		order.setPriority("M");
		order.setDate("01/01/2014");
		order.setShip_date("02/02/2014");
		order.setUnits_sold(1593);
		order.setUnit_cost(6.92);
		order.setUnit_price(9.33);
		order.setTotal_revenue(1482.34);
		order.setTotal_cost(11023.56);
		order.setTotal_profit(3839.13);
		content.add(order);
		pageOrder.setContent(content); 
		pageOrder.setLinks(link);
		pageOrder.setPage(1);
		

		int[][] pedidos =new int[][] {new int[] {1,2},new int[] {1,2}};
		 
		when(jdbcTemplate.batchUpdate(any(String.class),
				any(List.class), any(Integer.class),any(ParameterizedPreparedStatementSetter .class))).thenReturn(pedidos);
		 int[][] resultado = saveOrdersService.guardarPedidos(pageOrder);
		
		Assertions.assertEquals(resultado, pedidos);

	}
}
