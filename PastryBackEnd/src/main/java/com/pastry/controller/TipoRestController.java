package com.pastry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pastry.model.Categoria;
import com.pastry.model.Marca;
import com.pastry.model.Tipo;
import com.pastry.service.api.CategoriaServiceAPI;
import com.pastry.service.api.MarcaServiceAPI;
import com.pastry.service.api.TipoServiceAPI;

@RestController
@RequestMapping(value="/apiT")
@CrossOrigin({"*"})
public class TipoRestController {
	
	@Autowired
	private TipoServiceAPI tipoServiceAPI;
	
	@GetMapping(value="/all")
	public List<Tipo>getAll(){
		return tipoServiceAPI.getAll(); 
	}
	
	
}
