package com.pastry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.pastry.commons.GenericServiceImpl;
import com.pastry.dao.api.MarcaDaoAPI;
import com.pastry.dao.api.ProductoDaoAPI;
import com.pastry.model.Marca;
import com.pastry.model.Producto;
import com.pastry.service.api.MarcaServiceAPI;
import com.pastry.service.api.ProductoServiceAPI;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, Long>implements ProductoServiceAPI{

	@Autowired
	private ProductoDaoAPI productoDaoAPI;
	
	@Override
	public CrudRepository<Producto, Long> getDao() {
		return productoDaoAPI;
	}

}
