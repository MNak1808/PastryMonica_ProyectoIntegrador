package com.pastry.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastry.dao.api.OrdenDaoAPI;
import com.pastry.model.Orden;
import com.pastry.model.Usuario;
import com.pastry.service.api.OrdenServiceAPI;

@Service
public class OrdenServiceImpl implements OrdenServiceAPI{

	@Autowired
	private OrdenDaoAPI ordenDaoAPI;
	
	@Override
	public List<Orden> findAll() {
		return ordenDaoAPI.findAll();
	}

	@Override
	public Orden findById(Integer id) {
		Optional<Orden> obj = ordenDaoAPI.findById(id);
		if (obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	public Orden save(Orden orden) {
		return ordenDaoAPI.save(orden);
	}

	@Override
	public List<Orden> findByUsuarioid(Integer usuarioid) {
		return ordenDaoAPI.findByUsuarioid(usuarioid);
	}

}
