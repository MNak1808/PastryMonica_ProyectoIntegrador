package com.pastry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.pastry.commons.GenericServiceImpl;
import com.pastry.dao.api.CategoriaDaoAPI;
import com.pastry.dao.api.UsuarioDAOAPI;
import com.pastry.model.Categoria;
import com.pastry.model.Usuario;
import com.pastry.service.api.CategoriaServiceAPI;
import com.pastry.service.api.UsuarioServiceAPI;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long>implements UsuarioServiceAPI{

	@Autowired
	private UsuarioDAOAPI usuarioDaoAPI;
	
	@Override
	public CrudRepository<Usuario, Long> getDao() {
		return usuarioDaoAPI;
	}

}
