package com.example.pruebatecnica.controller;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import com.example.pruebatecnica.appconfig.AppConfig;
import com.example.pruebatecnica.repository.OrderRepository;
import com.example.pruebatecnica.repository.impl.OrderRepositoryImpl;
import com.example.pruebatecnica.service.createcsv.CreateCSVService;
import com.example.pruebatecnica.service.createcsv.impl.CreateCSVServiceImpl;
import com.example.pruebatecnica.service.dto.OrdersSummary;
import com.example.pruebatecnica.service.dto.PageOrder;
import com.example.pruebatecnica.service.getordersapi.GetOrdersApiService;
import com.example.pruebatecnica.service.getordersapi.impl.GetOrdersApiServiceImpl;
import com.example.pruebatecnica.service.getsummary.GetSummaryService;
import com.example.pruebatecnica.service.getsummary.impl.GetSummaryServiceImpl;
import com.example.pruebatecnica.service.saveorders.SaveOrdersService;
import com.example.pruebatecnica.service.saveorders.impl.SaveOrdersServiceImpl;
import com.example.pruebatecnica.utils.Utils;

public class OrderController {

	private static final String API_URL = "api.url";
	private static final String API_PAGE = "api.page";
	private static final String API_MAX_PER_PAGE = "api.maxPerPage";

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	public String url;
	public String page;
	public String maxPerPage;

	PageOrder pageOrder;
	Properties properties = new Properties();

	CreateCSVService createCsvService;
	SaveOrdersService saveOrdersService;
	GetSummaryService getSummaryService;
	GetOrdersApiService getOrdersApiService;
	JdbcTemplate jdbcTemplate = new JdbcTemplate(AppConfig.dataSource());

	public OrderController() throws Exception {
		super();
	}

	/**
	 * Realiza el volcado de datos de la api a la bbdd y muestra un resumen en pantalla de los datos procesados
	 * @return OrdersSummary objeto con el resumen de los datos
	 * @throws Exception
	 */
	public OrdersSummary realizarOperacion() throws Exception   {

		obtenerProperties();
		String link = url + "/orders" + "?page=" + page + "&max-per-page=" + maxPerPage;
		boolean existeLink = true;
		while (existeLink) {
			PageOrder pageOrder;
			pageOrder = obtenerPaginaApi(link);
			
			if (pageOrder != null && pageOrder.getLinks() != null && pageOrder.getLinks().getNext() != null) {
				link = pageOrder.getLinks().getNext();

			} else {
				existeLink = false;
			}
			OrderRepository repositorySave = new OrderRepositoryImpl(jdbcTemplate);
			saveOrdersService = new SaveOrdersServiceImpl(repositorySave);
			saveOrdersService.guardarPedidos(pageOrder);
		}
		OrderRepository repositoryGet = new OrderRepositoryImpl(jdbcTemplate); 
		getSummaryService = new GetSummaryServiceImpl(repositoryGet);
		OrdersSummary response = getSummaryService.obtenerResumen();
		return response;
	}

	/**
	 * Genera un archivo CSV y lo devuelve para su posterior descarga
	 * @return
	 * @throws IOException
	 */
	public File obtenerFile() throws Exception {
		OrderRepository repositoryCsv = new OrderRepositoryImpl(jdbcTemplate);
		createCsvService = new CreateCSVServiceImpl(repositoryCsv);
		return createCsvService.createCSV();
	}

	/**
	 * Metodo para obtener los literales de application.properties
	 * 
	 * @throws IOException
	 * 
	 * @throws Exception
	 */
	private void obtenerProperties() throws IOException  {
		Utils utils = new Utils();
		try {
			url = utils.obtenerProperties(API_URL);
			page = utils.obtenerProperties(API_PAGE);
			maxPerPage = utils.obtenerProperties(API_MAX_PER_PAGE);
		} catch (IOException e) {
			String error = "Error al obtener los literales para la conexion con la api ";
			logger.debug(error+e.getMessage());
			throw new IOException(error);
		}
		
	}

	/**
	 * Realzar la llamada a la api para obtener una pagina de pedidos a partir de un
	 * link
	 * 
	 * @param link
	 * @throws Exception
	 */
	private PageOrder obtenerPaginaApi(String link) throws Exception {
		PageOrder pageOrder = null;
		try {
			logger.info("La url actual es: " + link);
			getOrdersApiService = new GetOrdersApiServiceImpl(new RestTemplate());
			pageOrder = getOrdersApiService.obtenerPagina(link);
			logger.info("La obtención de la página de pedidos ha devuelto: " + pageOrder.toString());
		} catch (Exception e) {
			String error = "Error al leer de la api ";
			logger.debug(error+e.getMessage());
			throw new Exception(error);
		}
		return pageOrder;
	}
}
