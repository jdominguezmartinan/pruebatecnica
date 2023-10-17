package com.example.pruebatecnica.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageOrder{

	private Integer page;
	private List<Order> content;
	private Link links;
	
	
}
