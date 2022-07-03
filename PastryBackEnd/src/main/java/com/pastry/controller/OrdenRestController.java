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

import com.pastry.model.Marca;
import com.pastry.model.Orden;
import com.pastry.service.api.OrdenServiceAPI;

@RestController
@RequestMapping(value="/apiO")
@CrossOrigin({"*"})
public class OrdenRestController {
	
	@Autowired
	private OrdenServiceAPI ordenServiceAPI;
	
	@GetMapping("/all")
	public List<Orden>getAll(){
		return ordenServiceAPI.findAll();
	}
	
	@GetMapping(value = "/find/{id}")
	public Orden find(@PathVariable Integer id) {
		return ordenServiceAPI.findById(id);
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<Orden>save(@RequestBody Orden orden){
		try {
			Orden obj=ordenServiceAPI.save(orden);
			return new ResponseEntity<Orden>(obj,HttpStatus.OK);
		}catch (Exception e) {
			System.out.println("Error: "+e);
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}		
	}
	
	@GetMapping(value = "/findU/{id}")
	public List< Orden >findU(@PathVariable Integer id) {
		return ordenServiceAPI.findByUsuarioid(id);
	}
}
