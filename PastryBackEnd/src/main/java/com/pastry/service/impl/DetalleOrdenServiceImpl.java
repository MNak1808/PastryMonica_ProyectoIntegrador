package com.pastry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pastry.dao.api.DetalleOrdenDaoAPI;
import com.pastry.model.DetalleOrden;
import com.pastry.service.api.DetalleOrdenService;

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService{

	@Autowired
	private DetalleOrdenDaoAPI detalleOrdenDaoAPI;
	
	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenDaoAPI.save(detalleOrden);
	}

}
