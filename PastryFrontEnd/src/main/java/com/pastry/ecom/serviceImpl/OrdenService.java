package com.pastry.ecom.serviceImpl;

import java.util.List;

import com.pastry.ecom.dto.OrdenDTO;


public interface OrdenService  {
	
	public List<OrdenDTO> findAll();
	
	public OrdenDTO findById(String id);
	
	public boolean save(OrdenDTO orden);
	
	public List<OrdenDTO> findByUsuarioid(String usuarioid);
}
