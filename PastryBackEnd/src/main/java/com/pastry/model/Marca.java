package com.pastry.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name="tb_marca")
public class Marca implements Serializable {
	
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_marca;
	
	@NotEmpty
	private String descr_marca;
	
	@NotEmpty
	private String vendedor;
	
	@NotEmpty
	private String telefono;
	
	@NotEmpty
	private String correo;
	
}
