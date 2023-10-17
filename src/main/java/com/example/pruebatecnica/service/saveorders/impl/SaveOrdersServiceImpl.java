package com.example.pruebatecnica.service.saveorders.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.pruebatecnica.repository.OrderRepository;
import com.example.pruebatecnica.service.dto.PageOrder;
import com.example.pruebatecnica.service.saveorders.SaveOrdersService;

@Service
public class SaveOrdersServiceImpl implements SaveOrdersService {

	private static final Logger logger = LoggerFactory.getLogger(SaveOrdersServiceImpl.class);

	private final OrderRepository repository;

	public SaveOrdersServiceImpl(OrderRepository repository) {
		this.repository = repository;
	}
 
	@Override
	public int[][] guardarPedidos(PageOrder paginaPedidos) throws Exception  {

		logger.info("Entrada en metodo para guardar pedidos");

		int[][] response = null;

		logger.info("SaveOrdersServiceImpl - guardarPedidos: " + paginaPedidos.toString());
		try {
			response =repository.saveAll(paginaPedidos.getContent());
			logger.info("SaveOrdersServiceImpl - guardarPedidos: se han guardado " + paginaPedidos.getContent().toString());
		} catch (Exception e) {
			
			logger.debug("No se ha podido realizar la inserccion en bbdd , existen violaciones de clave primaria: "+paginaPedidos.getContent().toString());
			
		}
		return response;
	}

}
