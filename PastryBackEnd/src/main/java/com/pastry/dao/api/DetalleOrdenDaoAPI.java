package com.pastry.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pastry.model.DetalleOrden;

@Repository
public interface DetalleOrdenDaoAPI extends JpaRepository<DetalleOrden, Integer>{
	
}
