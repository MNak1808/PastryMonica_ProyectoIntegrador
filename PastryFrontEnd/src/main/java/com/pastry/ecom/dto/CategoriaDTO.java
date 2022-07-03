package com.pastry.ecom.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoriaDTO implements Serializable{
	
	private long cod_cat;
	private String descrip_cat;
	
}
