package com.example.pruebatecnica.service.createcsv;

import java.io.File;
import java.io.IOException;

public interface CreateCSVService {
	/**
	 * Generar archivo CSV
	 * @return
	 * @throws IOException
	 * @throws Exception 
	 */
	File createCSV() throws IOException, Exception;
}
