package com.pastry.ecom.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pastry.ecom.dto.OrdenDTO;
import com.pastry.ecom.serviceImpl.OrdenServiceImpl;
import com.pastry.ecom.util.Paginas;

@Controller
@RequestMapping("/orden")
public class OrdenController {
	
	@Autowired
	private OrdenServiceImpl ordenservice;
	
	@GetMapping(value="/compras")
	public ModelAndView listarCompras (HttpSession session){
		ModelAndView modelAndView=new ModelAndView(Paginas.COMPRASU);
		String id= session.getAttribute("idusuario").toString();
		List<OrdenDTO> orden = ordenservice.findByUsuarioid(id);
		modelAndView.addObject("orden",orden);
		return modelAndView;
	}
	
	@GetMapping(value="/all")
	public ModelAndView listarOrden (HttpSession session){
		ModelAndView modelAndView=new ModelAndView(Paginas.COMPRAS);
		List<OrdenDTO> orden = ordenservice.findAll();
		modelAndView.addObject("orden",orden);
		return modelAndView;
	}
	
}
