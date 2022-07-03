package com.pastry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pastry.model.DetalleOrden;
import com.pastry.service.api.DetalleOrdenService;

@RestController
@RequestMapping(value="/apiD")
@CrossOrigin({"*"})
public class DetalleOrdenRestController {
	
	@Autowired
	private DetalleOrdenService detalleOrdenService;
	
	@PostMapping(value="/save")
	public ResponseEntity<DetalleOrden>save(@RequestBody DetalleOrden detalle){
		try {
			DetalleOrden obj=detalleOrdenService.save(detalle);
			return new ResponseEntity<DetalleOrden>(obj,HttpStatus.OK);
		}catch (Exception e) {
			System.out.println("Error: "+e);
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

}
