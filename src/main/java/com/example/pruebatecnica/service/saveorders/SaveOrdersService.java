package com.example.pruebatecnica.service.saveorders;

import com.example.pruebatecnica.service.dto.PageOrder;

public interface SaveOrdersService {
	
	/**
	 * almacenar la pagina de pedidos obtenida de la api a bbdd
	 * @param paginaPedidos
	 * @return 
	 * @throws Exception 
	 */
	int[][] guardarPedidos(PageOrder paginaPedidos) throws Exception  ;
}
