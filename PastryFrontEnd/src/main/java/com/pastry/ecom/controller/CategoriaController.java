package com.pastry.ecom.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pastry.ecom.dto.CategoriaDTO;
import com.pastry.ecom.serviceImpl.CategoriaServiceImpl;
import com.pastry.ecom.util.Paginas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	@Qualifier("categoriaservice")
	private CategoriaServiceImpl categoriaservice;
	
	//url: http://localhost:3373/categoria/all
	@GetMapping(value="/all")
	public ModelAndView listarCategoria (){
		ModelAndView modelAndView=new ModelAndView(Paginas.LISTAC);
		modelAndView.addObject("categoria",categoriaservice.listarC());
		return modelAndView;
	}
	
	//url: http://localhost:8080/categoria/create
	@GetMapping(value="/create")
	public ModelAndView createCategoria() {
			
		ModelAndView modelAndView = new ModelAndView(Paginas.CRUDC);
		modelAndView.addObject("categoria", new CategoriaDTO());
			
		return modelAndView;
	}
	
	
	//url: http://localhost:8080/categoria/save
	@PostMapping("/save")
	public ModelAndView saveCategoria(CategoriaDTO categoria, Model model) {
		System.out.println(categoria.toString());
		
		categoriaservice.guardarC(categoria);
		
		ModelAndView modelAndView = new ModelAndView(Paginas.LISTAC);
		modelAndView.addObject("categoria", categoriaservice.listarC());
			
		return modelAndView;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editC(@PathVariable("id") String id) {
		
		ModelAndView modelAndView = new ModelAndView(Paginas.CRUDC);
		if(id!=null) {
			modelAndView.addObject("categoria", categoriaservice.buscarC(id));
		}
		else modelAndView.addObject("categoria", new CategoriaDTO());
			
		return modelAndView;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView deleteC(@PathVariable("id") String id) {
		
		ModelAndView modelAndView = new ModelAndView(Paginas.LISTAC);
		if(id!=null) {
			categoriaservice.deleteC(id);
			modelAndView.addObject("categoria", categoriaservice.listarC());
		}
		else {
			System.out.println("Error al eliminar la categoria con codigo "+id);			
			modelAndView.addObject("categoria", categoriaservice.listarC());
		}
			
		return modelAndView;
	}
	
	
}
