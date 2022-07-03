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
import com.pastry.service.api.CategoriaServiceAPI;

@RestController
@RequestMapping(value="/apiC")
@CrossOrigin({"*"})
public class CategoriaRestController {
	
	@Autowired
	private CategoriaServiceAPI categoriaServiceAPI;
	
	@GetMapping(value="/all")
	public List<Categoria>getAll(){
		return categoriaServiceAPI.getAll();
	}
	
	@GetMapping(value = "/find/{id}")
	public Categoria find(@PathVariable Long id) {
		return categoriaServiceAPI.get(id);
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<?>save(@RequestBody Categoria categoria){
		try {
			Categoria obj=categoriaServiceAPI.save(categoria);
			return new ResponseEntity<Categoria>(obj,HttpStatus.OK);
		}catch (Exception e) {
			System.out.println("Error: "+e);
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping(value="/delete/{id}")
	public ResponseEntity<Categoria>delete(@PathVariable Long id){
		Categoria categoria=categoriaServiceAPI.get(id);
		if(categoria!=null) {
			categoriaServiceAPI.delete(id);
		}else {
			return new ResponseEntity<Categoria>(categoria,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Categoria>(categoria,HttpStatus.OK);
	}
}
