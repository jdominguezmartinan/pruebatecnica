package com.example.pruebatecnica.service.getordersapi;

import com.example.pruebatecnica.service.dto.PageOrder;

public interface GetOrdersApiService{
	
	/**
	 * Obtener una de las paginas de respuesta del api a partir de su correspondiente link
	 * @param link
	 * @return
	 * @throws Exception
	 */
	PageOrder obtenerPagina(String link)throws Exception;
}