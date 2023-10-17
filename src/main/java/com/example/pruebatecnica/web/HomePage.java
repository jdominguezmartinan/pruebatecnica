package com.example.pruebatecnica.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.pruebatecnica.web.form.ApiToBDForm;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());

	public HomePage(final PageParameters parameters) {
		super(parameters);
		add(new ApiToBDForm("orderForm"));
		
		
	}
}
