package com.example.pruebatecnica.repository;

import java.text.ParseException;
import java.util.List;

import com.example.pruebatecnica.model.OrderEntity;
import com.example.pruebatecnica.service.dto.Order;
import com.example.pruebatecnica.service.dto.Summary;

public interface OrderRepository  {
	int[][] saveAll(List<Order> orders) throws ParseException;
	public List<Summary> findByRegion();
	public List<Summary> findByCountry();
	public List<Summary> findByItemType();
	public List<Summary> findBySalesChannel();
	public List<Summary> findByPriority();
	public List<OrderEntity> findAllOrderByOrderId();
}
