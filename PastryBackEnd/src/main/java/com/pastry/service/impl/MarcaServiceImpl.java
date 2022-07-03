package com.pastry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.pastry.commons.GenericServiceImpl;
import com.pastry.dao.api.MarcaDaoAPI;
import com.pastry.model.Marca;
import com.pastry.service.api.MarcaServiceAPI;

@Service
public class MarcaServiceImpl extends GenericServiceImpl<Marca, Long>implements MarcaServiceAPI{

	@Autowired
	private MarcaDaoAPI marcaDaoAPI;
	
	@Override
	public CrudRepository<Marca, Long> getDao() {
		return marcaDaoAPI;
	}

}
