package com.pastry.ecom.serviceImpl;

import java.util.List;

import com.pastry.ecom.dto.CategoriaDTO;
import com.pastry.ecom.dto.MarcaDTO;

public interface MarcaService {
	
	public List<MarcaDTO> listarM();
	
	public MarcaDTO buscarM(String id);
	
	public boolean guardarM(MarcaDTO marca);
	
	public boolean deleteM(String id);
	
}
