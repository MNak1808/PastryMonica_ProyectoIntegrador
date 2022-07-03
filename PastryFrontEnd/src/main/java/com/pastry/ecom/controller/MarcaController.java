package com.pastry.ecom.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pastry.ecom.dto.CategoriaDTO;
import com.pastry.ecom.dto.MarcaDTO;
import com.pastry.ecom.serviceImpl.CategoriaServiceImpl;
import com.pastry.ecom.serviceImpl.MarcaServiceImpl;
import com.pastry.ecom.util.Paginas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/marca")
public class MarcaController {
	
	@Autowired
	@Qualifier("marcaservice")
	private MarcaServiceImpl marcaservice;
	
	//url: http://localhost:3373/marca/all
	@GetMapping(value="/all")
	public ModelAndView listarMarca (){
		ModelAndView modelAndView=new ModelAndView(Paginas.LISTAM);
		modelAndView.addObject("marca",marcaservice.listarM());
		return modelAndView;
	}
	
	//url: http://localhost:8080/marca/create
	@GetMapping(value="/create")
	public ModelAndView createMarca() {
			
		ModelAndView modelAndView = new ModelAndView(Paginas.CRUDM);
		modelAndView.addObject("marca", new MarcaDTO());
			
		return modelAndView;
	}
	
	
	//url: http://localhost:8080/marca/save
	@PostMapping("/save")
	public ModelAndView guardarMarca(MarcaDTO marca, Model model) {
		System.out.println(marca.toString());
		
		marcaservice.guardarM(marca);
		
		ModelAndView modelAndView = new ModelAndView(Paginas.LISTAM);
		modelAndView.addObject("marca", marcaservice.listarM());
			
		return modelAndView;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editM(@PathVariable("id") String id) {
		
		ModelAndView modelAndView = new ModelAndView(Paginas.CRUDM);
		if(id!=null) {
			modelAndView.addObject("marca", marcaservice.buscarM(id));
		}
		else modelAndView.addObject("marca", new MarcaDTO());
			
		return modelAndView;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView deleteM(@PathVariable("id") String id) {
		
		ModelAndView modelAndView = new ModelAndView(Paginas.LISTAM);
		if(id!=null) {
			marcaservice.deleteM(id);
			modelAndView.addObject("marca", marcaservice.listarM());
		}
		else {
			System.out.println("Error al eliminar la marca con codigo "+id);			
			modelAndView.addObject("marca", marcaservice.listarM());
		}
			
		return modelAndView;
	}
	
	
}
