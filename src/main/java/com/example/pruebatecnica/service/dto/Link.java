package com.example.pruebatecnica.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Link{
	
	private String self;
    private String next;
    private String prev;
}
