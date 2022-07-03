package com.pastry.dao.api;

import org.springframework.data.repository.CrudRepository;

import com.pastry.model.Producto;

public interface ProductoDaoAPI extends CrudRepository<Producto, Long> {
	
}
