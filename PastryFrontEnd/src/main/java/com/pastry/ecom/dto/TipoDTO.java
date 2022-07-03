package com.pastry.ecom.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TipoDTO implements Serializable{
	
	private Integer id_tipo;
	private String descripcion;
	
}
