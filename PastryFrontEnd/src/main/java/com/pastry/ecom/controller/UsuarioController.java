package com.pastry.ecom.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pastry.ecom.dto.MarcaDTO;
import com.pastry.ecom.dto.UsuarioDTO;
import com.pastry.ecom.serviceImpl.TipoServiceImpl;
import com.pastry.ecom.serviceImpl.UsuarioService;
import com.pastry.ecom.serviceImpl.UsuarioServiceImpl;
import com.pastry.ecom.util.Paginas;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioServiceImpl usuarioservice;
	
	@Autowired
	private TipoServiceImpl tiposervice;
	
	BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
	
	@GetMapping(value="/all")
	public ModelAndView listarUsuario (){
		ModelAndView modelAndView=new ModelAndView(Paginas.LISTAU);
		modelAndView.addObject("usuario",usuarioservice.listarU());
		modelAndView.addObject("tipo",tiposervice.listarT());	
		return modelAndView;
	}
	
	@GetMapping(value="/create")
	public ModelAndView crearUsuario() {
			
		ModelAndView modelAndView = new ModelAndView(Paginas.CRUDU);
		modelAndView.addObject("usuario", new UsuarioDTO());
		modelAndView.addObject("tipo",tiposervice.listarT());	
		return modelAndView;
	}
	
	@GetMapping(value="/registrar")
	public ModelAndView registrarUsuario() {
			
		ModelAndView modelAndView = new ModelAndView(Paginas.REGISTRO);
		modelAndView.addObject("usuario", new UsuarioDTO());
			
		return modelAndView;
	}
	
	@PostMapping("/save")
	public ModelAndView guardarUser(UsuarioDTO usuario) {
		System.out.println(usuario.toString());
		usuario.setPass(passEncode.encode(usuario.getPass()));
		try {
		usuarioservice.guardarU(usuario);
		System.out.println("se guardo backend");
		}catch (Exception e) {
			System.out.println("Error: "+e);
		}
		ModelAndView modelAndView = new ModelAndView(Paginas.LISTAU);
		modelAndView.addObject("usuario", usuarioservice.listarU());
		modelAndView.addObject("tipo",tiposervice.listarT());	
		return modelAndView;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editU(@PathVariable("id") String id) {
				
		if(id!=null) {
			ModelAndView modelAndView = new ModelAndView(Paginas.EDITU);
			modelAndView.addObject("usuario", usuarioservice.buscarU(id));
			modelAndView.addObject("tipo",tiposervice.listarT());
			return modelAndView;
		}
		else {
			ModelAndView modelAndView = new ModelAndView(Paginas.CRUDU);
			modelAndView.addObject("usuario", new UsuarioDTO());
			modelAndView.addObject("tipo",tiposervice.listarT());
			return modelAndView;
		}
					
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView deleteU(@PathVariable("id") String id) {
		
		ModelAndView modelAndView = new ModelAndView(Paginas.LISTAU);
		if(id!=null) {
			usuarioservice.deleteU(id);
			modelAndView.addObject("usuario", usuarioservice.listarU());
			modelAndView.addObject("tipo",tiposervice.listarT());
		}
		else {
			System.out.println("Error al eliminar la marca con codigo "+id);			
			modelAndView.addObject("usuario", usuarioservice.listarU());
			modelAndView.addObject("tipo",tiposervice.listarT());
		}
			
		return modelAndView;
	}
	
	@PostMapping("/save2")
	public String editarUsuario(UsuarioDTO usuario,Model model) {
		System.out.println(usuario.toString());
		usuario.setPass(passEncode.encode(usuario.getPass()));
		try {
		usuarioservice.guardarU(usuario);
		model.addAttribute("mensaje","Se actualizo el usuario");
		model.addAttribute("alert","success");
		}catch (Exception e) {
			model.addAttribute("mensaje","Error al actualizar");
			model.addAttribute("alert","danger");
			return "redirect:/usuario/editar";
		}
		return "redirect:/";
	}
	
	@PostMapping("/save1")
	public String guardarUsuario(UsuarioDTO usuario) {
		System.out.println(usuario.toString());
		usuario.setPass(passEncode.encode(usuario.getPass()));
		usuarioservice.guardarU(usuario);
		
		return "redirect:/login";
	}
	
	@GetMapping("/editar")
	public ModelAndView editarU(HttpSession session) {
		String idU=session.getAttribute("idusuario").toString();		
		
		ModelAndView modelAndView = new ModelAndView(Paginas.EDITU2);
		modelAndView.addObject("usuario", usuarioservice.buscarU(idU));
		return modelAndView;
							
	}
	
}
