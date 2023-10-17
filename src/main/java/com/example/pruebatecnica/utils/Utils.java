package com.example.pruebatecnica.utils;

import java.io.IOException;
import java.util.Properties;

public class Utils {

	private static final String APPLICATION_PROPERTIES = "/application.properties";

	/**
	 * Metodo para obtener los literales de application.properties
	 * @throws IOException 
	 * 
	 * @throws Exception
	 */
	public String obtenerProperties(String cadena) throws IOException {
		Properties properties = new Properties();
		String prop = null;

		properties.load(this.getClass().getResourceAsStream(APPLICATION_PROPERTIES));
		prop = properties.getProperty(cadena);

		return prop;
	}
}
