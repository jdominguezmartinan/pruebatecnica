package com.example.pruebatecnica.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.pruebatecnica.service.dto.Link;
import com.example.pruebatecnica.service.dto.Order;
import com.example.pruebatecnica.service.dto.PageOrder;
import com.example.pruebatecnica.service.getordersapi.impl.GetOrdersApiServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GetOrdersApiServiceTest {

	@Mock
	RestTemplate restTemplate;

	private GetOrdersApiServiceImpl getOrdersApiService;

	@BeforeEach
	public void setUp() {
		getOrdersApiService = new GetOrdersApiServiceImpl(restTemplate);
	}

	@Test
	public void obtenerPagina() throws Exception {
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

		ResponseEntity<PageOrder> responseEntity = new ResponseEntity<PageOrder>(pageOrder, HttpStatus.OK);

		String link1 = "https://kata-espublicotech.g3stiona.com:443/v1/orders?page=1&max-per-page=1000";

		when(restTemplate.getForEntity(link1, PageOrder.class)).thenReturn(responseEntity);
		final PageOrder resultado = getOrdersApiService.obtenerPagina(link1);
		Assertions.assertEquals(1, resultado.getPage());
		Assertions.assertEquals(content, resultado.getContent());
		Assertions.assertEquals(link, resultado.getLinks());

	}

}
