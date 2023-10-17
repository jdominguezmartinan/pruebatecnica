package com.example.pruebatecnica.web;

import java.io.File;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.pruebatecnica.controller.OrderController;
import com.example.pruebatecnica.service.dto.OrdersSummary;
import com.example.pruebatecnica.service.dto.Summary;

public class ResultadoPage extends WebPage {

	private static final long serialVersionUID = -9152202659140336363L;
	
	OrdersSummary summary;
	static final Logger logger = LoggerFactory.getLogger(ResultadoPage.class);

	public ResultadoPage(OrdersSummary summary) throws Exception {
		this.summary = summary;

		addLinkCsv();
		
		List<Summary> country = summary.getCountry();
		List<Summary> itemType = summary.getItemType();
		List<Summary> orderPriority = summary.getOrderPriority();
		List<Summary> region = summary.getRegion();
		List<Summary> salesChannel = summary.getSalesChannel();
		
		anadirTabla(country,"listCountrys","clave","valor");
		anadirTabla(itemType,"listItemType","clave2","valor2");
		anadirTabla(orderPriority,"listOrderRepository","clave3","valor3");
		anadirTabla(region,"listRegion","clave4","valor4");
		anadirTabla(salesChannel,"listSalesChannel","clave5","valor5");

	}

	/**
	 * metodo para pintar por pantalla cada una de las tablas del resumen
	 * @param summary
	 * @param idSummary
	 * @param labelKey
	 * @param valueKey
	 */
	private void anadirTabla(List<Summary> summary,String idSummary,String labelKey,String valueKey) {
		add(new ListView<Summary>(idSummary, summary) {
			
			private static final long serialVersionUID = -988672949223941397L;

			@Override
			protected void populateItem(ListItem<Summary> item) {
				Summary tmpSummary = item.getModelObject();
				item.add(new Label(labelKey, tmpSummary.getClave()));
				item.add(new Label(valueKey, tmpSummary.getValor()));

			}
		});
	}

	/**
	 * Añadir link en pantalla para descarga del archivo csv
	 * @throws Exception
	 */
	private void addLinkCsv() throws Exception {
		File file = null;
		OrderController orderController = new OrderController();
		file = orderController.obtenerFile();
		DownloadLink link = new DownloadLink("csv", file);
		add(link);
	}
}
