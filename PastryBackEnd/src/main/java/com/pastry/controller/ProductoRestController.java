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
import com.pastry.model.Producto;
import com.pastry.service.api.CategoriaServiceAPI;
import com.pastry.service.api.MarcaServiceAPI;
import com.pastry.service.api.ProductoServiceAPI;

@RestController
@RequestMapping(value="/apiP")
@CrossOrigin({"*"})
public class ProductoRestController {
	
	
	@Autowired
	private ProductoServiceAPI productoServiceAPI;
	
	@GetMapping(value="/all")
	public List<Producto>getAll(){
		return productoServiceAPI.getAll(); 
	}
	
	@GetMapping(value = "/find/{id}")
	public Producto find(@PathVariable Long id) {
		return productoServiceAPI.get(id);
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<Producto>save(@RequestBody Producto producto){
		
			Producto obj=productoServiceAPI.save(producto);
			return new ResponseEntity<Producto>(obj,HttpStatus.OK);
		
		
	}
	
	@PostMapping(value="/delete/{id}")
	public ResponseEntity<Producto>delete(@PathVariable Long id){
		Producto producto=productoServiceAPI.get(id);
		if(producto!=null) {
			productoServiceAPI.delete(id);
		}else {
			return new ResponseEntity<Producto>(producto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Producto>(producto,HttpStatus.OK);
	}
}
