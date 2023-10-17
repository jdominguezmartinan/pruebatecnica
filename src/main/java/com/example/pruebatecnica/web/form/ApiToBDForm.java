package com.example.pruebatecnica.web.form;

import org.apache.wicket.markup.html.form.Form;

import com.example.pruebatecnica.controller.OrderController;
import com.example.pruebatecnica.service.dto.OrdersSummary;
import com.example.pruebatecnica.web.ResultadoPage;

public class ApiToBDForm extends Form<Object> {

	private static final long serialVersionUID = -6402583150309394597L;

	public ApiToBDForm(String id) {
		super(id);
	}

	OrdersSummary summary = null;

	@Override
	public void onSubmit() {
		super.onSubmit();
		try {
			OrderController controller = new OrderController();
			summary = controller.realizarOperacion();
			ResultadoPage resultadoPage = new ResultadoPage(summary);
			setResponsePage(resultadoPage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
