package com.example.pruebatecnica.service.getordersapi.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.pruebatecnica.service.dto.PageOrder;
import com.example.pruebatecnica.service.getordersapi.GetOrdersApiService;

@Service
public class GetOrdersApiServiceImpl implements GetOrdersApiService{
	
	private static final String EXCEPTION_NO_RESULTS = "La consulta no ha devuelto resultados";
	private static final Logger log = LoggerFactory.getLogger(GetOrdersApiServiceImpl.class);
	protected final  RestTemplate restTemplate;
	
	 public GetOrdersApiServiceImpl(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }
	
	 @Override
	public PageOrder obtenerPagina(String link) throws Exception{
		
		log.debug("Entrada metodo obtener pedidos");
		ResponseEntity<PageOrder> response = null;
		try {
			 response = restTemplate.getForEntity(link,PageOrder.class);
			if(response!=null && response.getStatusCode()!=null && response.getStatusCode().is2xxSuccessful()) {
				log.debug("Respuesta para la url: "+link+" "+response.toString());
				return response.getBody();
			}else {
				throw new Exception(EXCEPTION_NO_RESULTS); 
			}
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

}
