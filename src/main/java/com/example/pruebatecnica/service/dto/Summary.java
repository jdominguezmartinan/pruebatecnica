package com.example.pruebatecnica.service.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Summary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1036713813554197931L;
	String clave;
	String Valor;
}
