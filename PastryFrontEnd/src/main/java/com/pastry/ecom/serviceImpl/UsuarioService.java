package com.pastry.ecom.serviceImpl;

import java.util.List;

import com.pastry.ecom.dto.CategoriaDTO;
import com.pastry.ecom.dto.MarcaDTO;
import com.pastry.ecom.dto.UsuarioDTO;

public interface UsuarioService {
	
	public List<UsuarioDTO> listarU();
	
	public UsuarioDTO buscarU(String id);
	
	public UsuarioDTO buscarByUsuario(String usuario);
	
	public boolean guardarU(UsuarioDTO usuario);
	
	public boolean deleteU(String id);
	
}
