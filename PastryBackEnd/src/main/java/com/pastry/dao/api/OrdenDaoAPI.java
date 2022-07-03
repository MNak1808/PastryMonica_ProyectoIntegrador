package com.pastry.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pastry.model.Orden;
import com.pastry.model.Usuario;

@Repository
public interface OrdenDaoAPI extends JpaRepository<Orden, Integer>{
	
	List<Orden>findByUsuarioid(Integer usuarioid);
	
}
