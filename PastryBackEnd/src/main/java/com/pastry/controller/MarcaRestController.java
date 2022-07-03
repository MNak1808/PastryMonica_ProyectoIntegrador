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
import com.pastry.service.api.CategoriaServiceAPI;
import com.pastry.service.api.MarcaServiceAPI;

@RestController
@RequestMapping(value="/apiM")
@CrossOrigin({"*"})
public class MarcaRestController {
	
	@Autowired
	private MarcaServiceAPI marcaServiceAPI;
	
	@GetMapping(value="/all")
	public List<Marca>getAll(){
		return marcaServiceAPI.getAll(); 
	}
	
	@GetMapping(value = "/find/{id}")
	public Marca find(@PathVariable Long id) {
		return marcaServiceAPI.get(id);
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<Marca>save(@RequestBody Marca marca){
		try {
			Marca obj=marcaServiceAPI.save(marca);
			return new ResponseEntity<Marca>(obj,HttpStatus.OK);
		}catch (Exception e) {
			System.out.println("Error: "+e);
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping(value="/delete/{id}")
	public ResponseEntity<Marca>delete(@PathVariable Long id){
		Marca marca=marcaServiceAPI.get(id);
		if(marca!=null) {
			marcaServiceAPI.delete(id);
		}else {
			return new ResponseEntity<Marca>(marca,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Marca>(marca,HttpStatus.OK);
	}
}
