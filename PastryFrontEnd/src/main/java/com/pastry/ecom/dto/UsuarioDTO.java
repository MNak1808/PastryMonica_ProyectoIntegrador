package com.pastry.ecom.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable{
	private long codigo;
	private String nombre;
	private String apellido;
	private String usuario;
	private String pass;
	@Email
	private String correo;
	private int id_tipo;
}
