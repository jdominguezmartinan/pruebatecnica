package com.example.pruebatecnica;


import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.example.pruebatecnica.web.HomePage;

@Component
@EnableAutoConfiguration
@ComponentScan
public class PruebatecnicaApplication extends WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebatecnicaApplication.class, args);
	}
	
	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}
	@Override
	protected void init() {
	  super.init();
	  getCspSettings().blocking().disabled();
	}
}
