package com.pastry.ecom.serviceImpl;

import java.util.List;

import com.pastry.ecom.dto.CategoriaDTO;

public interface CategoriaService {
	
	public List<CategoriaDTO> listarC();
	
	public CategoriaDTO buscarC(String id);
	
	public boolean guardarC(CategoriaDTO categoria);
	
	public boolean deleteC(String id);
	
}
