package com.pastry.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="tb_producto")
public class Producto implements Serializable {
	
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long cod_prod;
	
	@NotEmpty
	private String descrip_prod;
	
	
	private int cod_cat;
	
	
	private int cod_marca;
	
	
	private double precio_prod;
	
	
	private int stock_prod;
	
}
