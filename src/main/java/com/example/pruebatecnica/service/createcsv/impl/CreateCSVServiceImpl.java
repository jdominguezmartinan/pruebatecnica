package com.example.pruebatecnica.service.createcsv.impl;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.pruebatecnica.model.OrderEntity;
import com.example.pruebatecnica.repository.OrderRepository;
import com.example.pruebatecnica.service.createcsv.CreateCSVService;
import com.opencsv.CSVWriter;

@Service
public class CreateCSVServiceImpl implements CreateCSVService {

	private static final Logger logger = LoggerFactory.getLogger(CreateCSVServiceImpl.class);
	private final OrderRepository orderRepository;

	public CreateCSVServiceImpl(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	@Override
	public File createCSV() throws Exception {
		
		logger.info("Entramos en el metodo de crear documento CSV");
		String path = "C:" + File.separator +"archivos"+ File.separator+"orders.csv";
		File csvOutputFile = new File(path);
		List<OrderEntity> listOrder = new ArrayList<>();
		CSVWriter writer = new CSVWriter(new FileWriter(csvOutputFile));
		
		String[] headers = { "Order ID", "Order Priority", "Order Date", "Region", "Country", "Item Type",
				"Sales Channel", "Ship Date", "Units Sold", "Unit Price", "Unit Cost", "Total Revenue", "Total Cost",
				"Total Profit" };
		
		writer.writeNext(headers);

		try {
			logger.info("CreateCSVServiceImpl::Realizando consulta");
			listOrder = orderRepository.findAllOrderByOrderId();
			logger.info("CreateCSVServiceImpl::Respuesta" + listOrder.toString());
			List<String[]> listToCSv = setListAsArray(listOrder);
			writer.writeAll((listToCSv));
			writer.flush();
		} catch (Exception e) {
			String error = "Error al generar el archivo CSV";
			logger.error(error+e.getMessage());
			throw new Exception(error);
		}finally {
			writer.close();			
		}
		logger.info("CreateCSVServiceImpl: CSV ha sido generado");
		return csvOutputFile;
		

	}

	/**
	 * devolver List<String> a partir de un List<OrderEntity> para generar el archivo CSV
	 * @param listOrder
	 * @return
	 */
	private List<String[]> setListAsArray(List<OrderEntity> listOrder) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<String[]> listAr = new ArrayList<>();
		for (OrderEntity order : listOrder) {
			String[] ar = new String[14];

			ar[0] = String.valueOf(order.getOrderid());
			ar[1] = order.getPriority();
			ar[2] = dateFormat.format(order.getOrderDate());
			ar[3] = order.getRegion();
			ar[4] = order.getCountry();
			ar[5] = order.getItemType();
			ar[6] = order.getSalesChannel();
			ar[7] = dateFormat.format(order.getShipDate());
			ar[8] = String.valueOf(order.getUnitsSold());
			ar[9] = String.valueOf(order.getUnitPrice().doubleValue());
			ar[10] = String.valueOf(order.getUnitCost().doubleValue());
			ar[11] = String.valueOf(order.getTotalRevenue().doubleValue());
			ar[12] = String.valueOf(order.getTotalCost().doubleValue());
			ar[13] = String.valueOf(order.getTotalProfit().doubleValue());

			listAr.add(ar);
		}

		return listAr;
	}

}
