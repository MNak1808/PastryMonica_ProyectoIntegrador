package com.pastry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.pastry.commons.GenericServiceImpl;
import com.pastry.dao.api.CategoriaDaoAPI;
import com.pastry.dao.api.TipoDaoAPI;
import com.pastry.model.Categoria;
import com.pastry.model.Tipo;
import com.pastry.service.api.CategoriaServiceAPI;
import com.pastry.service.api.TipoServiceAPI;

@Service
public class TipoServiceImpl extends GenericServiceImpl<Tipo, Integer>implements TipoServiceAPI{

	@Autowired
	private TipoDaoAPI tipoDaoAPI;
	
	@Override
	public CrudRepository<Tipo, Integer> getDao() {
		return tipoDaoAPI;
	}

}
