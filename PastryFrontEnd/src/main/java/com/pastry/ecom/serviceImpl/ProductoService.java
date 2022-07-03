package com.pastry.ecom.serviceImpl;

import java.util.List;

import com.pastry.ecom.dto.CategoriaDTO;
import com.pastry.ecom.dto.MarcaDTO;
import com.pastry.ecom.dto.ProductoDTO;

public interface ProductoService {
	
	public List<ProductoDTO> listarP();
	
	public ProductoDTO buscarP(String id);
	
	public boolean guardarP(ProductoDTO producto);
	
	public boolean deleteP(String id);
	
}
