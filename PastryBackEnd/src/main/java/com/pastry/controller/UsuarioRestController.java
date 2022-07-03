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

import com.pastry.dao.api.UsuarioDAOAPI;
import com.pastry.model.Producto;
import com.pastry.model.Usuario;
import com.pastry.service.api.UsuarioServiceAPI;

@RestController
@RequestMapping(value="/apiU")
@CrossOrigin({"*"})
public class UsuarioRestController {
	
	@Autowired
	private UsuarioDAOAPI usuarioDAO;
	
	@Autowired
	private UsuarioServiceAPI usuarioServiceAPI;
	
	@GetMapping(value = "/findU/{usuario}")
	public Usuario find(@PathVariable String usuario) {
		return usuarioDAO.findByUsuario(usuario);
	}
	
	@GetMapping(value="/all")
	public List<Usuario>getAll(){
		return usuarioServiceAPI.getAll();
	}
	
	@GetMapping(value = "/find/{id}")
	public Usuario find(@PathVariable Long id) {
		return usuarioServiceAPI.get(id);
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<Usuario>save(@RequestBody Usuario usuario){
			if(usuario.getId_tipo()==0) {
				usuario.setId_tipo(2);
			}
			Usuario obj=usuarioServiceAPI.save(usuario);
			return new ResponseEntity<Usuario>(obj,HttpStatus.OK);
		
		
	}
	
	@PostMapping(value="/delete/{id}")
	public ResponseEntity<Usuario>delete(@PathVariable Long id){
		Usuario usuario=usuarioServiceAPI.get(id);
		if(usuario!=null) {
			usuarioServiceAPI.delete(id);
		}else {
			return new ResponseEntity<Usuario>(usuario,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
	}
}
