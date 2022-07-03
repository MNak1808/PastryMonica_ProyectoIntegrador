package com.pastry.ecom.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MarcaDTO implements Serializable{
	private long cod_marca;
	private String descr_marca;
	private String vendedor;
	private String telefono;
	private String correo;
}
