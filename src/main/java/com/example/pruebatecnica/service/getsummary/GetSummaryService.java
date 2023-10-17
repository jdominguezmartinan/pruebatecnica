package com.example.pruebatecnica.service.getsummary;

import com.example.pruebatecnica.service.dto.OrdersSummary;

public interface GetSummaryService {
	/**
	 * Obtener los datos de resumen por los distitntos campos:region,country,itemType,salesChannel,OrderPriority 
	 * @return
	 */
	OrdersSummary obtenerResumen() ;
}
