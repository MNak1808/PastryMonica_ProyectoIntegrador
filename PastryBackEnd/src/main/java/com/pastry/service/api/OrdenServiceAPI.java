package com.pastry.service.api;

import java.util.List;
import java.util.Optional;

import com.pastry.model.Marca;
import com.pastry.model.Orden;
import com.pastry.model.Usuario;


public interface OrdenServiceAPI  {
	
	List<Orden> findAll();
	
	Orden findById(Integer id);
	
	Orden save(Orden orden);
	
	List<Orden> findByUsuarioid(Integer usuarioid);
}
