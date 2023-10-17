package com.example.pruebatecnica.appconfig;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.example.pruebatecnica.utils.Utils;

public class AppConfig {

	private static final String ERROR_AL_CREAR_CONEXION = "Error al crear la conexión con la base de datos";
	private static Utils utils = new Utils();
	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);


	/**
	 * Crear el objeto DataSource necesario para la conexion con la base de datos
	 * @return
	 * @throws Exception
	 */
	public static DataSource dataSource() throws Exception {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

		try {
			driverManagerDataSource.setUrl(utils.obtenerProperties("spring.datasource.url"));
			driverManagerDataSource.setUsername(utils.obtenerProperties("spring.datasource.username"));
			driverManagerDataSource.setPassword(utils.obtenerProperties("spring.datasource.password"));
			driverManagerDataSource.setDriverClassName(utils.obtenerProperties("spring.datasource.driver-class-name"));
		} catch (Exception e) {
			logger.debug(ERROR_AL_CREAR_CONEXION);
			e.printStackTrace();
			throw new Exception(ERROR_AL_CREAR_CONEXION);
		}
	

		return driverManagerDataSource;
	}
}
