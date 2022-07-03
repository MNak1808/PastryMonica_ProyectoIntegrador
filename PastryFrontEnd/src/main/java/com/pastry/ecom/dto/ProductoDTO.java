package com.pastry.ecom.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductoDTO implements Serializable{
	private long cod_prod;
	private String descrip_prod;
	private int cod_cat;
	private int cod_marca;
	private double precio_prod;
	private int stock_prod;
}
