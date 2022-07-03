package com.pastry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.pastry.commons.GenericServiceImpl;
import com.pastry.dao.api.CategoriaDaoAPI;
import com.pastry.model.Categoria;
import com.pastry.service.api.CategoriaServiceAPI;

@Service
public class CategoriaServiceImpl extends GenericServiceImpl<Categoria, Long>implements CategoriaServiceAPI{

	@Autowired
	private CategoriaDaoAPI categoriaDaoAPI;
	
	@Override
	public CrudRepository<Categoria, Long> getDao() {
		return categoriaDaoAPI;
	}

}
