package com.example.pruebatecnica.service.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrdersSummary implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3290144717156502663L;
	private List<Summary> region;
    private List<Summary> country;
    private List<Summary> itemType;
    private List<Summary> salesChannel;
    private List<Summary> orderPriority;
}
