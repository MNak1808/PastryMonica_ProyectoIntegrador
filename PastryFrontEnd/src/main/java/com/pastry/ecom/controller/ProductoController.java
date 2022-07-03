package com.pastry.ecom.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pastry.ecom.dto.CategoriaDTO;
import com.pastry.ecom.dto.MarcaDTO;
import com.pastry.ecom.dto.ProductoDTO;
import com.pastry.ecom.serviceImpl.CategoriaServiceImpl;
import com.pastry.ecom.serviceImpl.MarcaServiceImpl;
import com.pastry.ecom.serviceImpl.ProductoServiceImpl;
import com.pastry.ecom.util.Paginas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	@Qualifier("marcaservice")
	private MarcaServiceImpl marcaservice;
	
	@Autowired
	@Qualifier("categoriaservice")
	private CategoriaServiceImpl categoriaservice;
	
	@Autowired
	@Qualifier("productoservice")
	private ProductoServiceImpl productoservice;
	
	//url: http://localhost:3373/marca/all
	@GetMapping(value="/all")
	public ModelAndView listarProducto (){
		ModelAndView modelAndView=new ModelAndView(Paginas.LISTAP);
		modelAndView.addObject("marca",marcaservice.listarM());
		modelAndView.addObject("categoria",categoriaservice.listarC());
		modelAndView.addObject("producto",productoservice.listarP());
		return modelAndView;
	}
	
	//url: http://localhost:8080/marca/create
	@GetMapping(value="/create")
	public ModelAndView createProducto() {
			
		ModelAndView modelAndView = new ModelAndView(Paginas.CRUDP);
		modelAndView.addObject("producto", new ProductoDTO());
		modelAndView.addObject("marca",marcaservice.listarM());
		modelAndView.addObject("categoria",categoriaservice.listarC());
			
		return modelAndView;
	}
	
	
	//url: http://localhost:8080/marca/save
	@PostMapping("/save")
	public ModelAndView guardarProducto(ProductoDTO producto, Model model) {
		System.out.println(producto.toString());
		
		productoservice.guardarP(producto);
		
		ModelAndView modelAndView = new ModelAndView(Paginas.LISTAP);
		modelAndView.addObject("marca",marcaservice.listarM());
		modelAndView.addObject("categoria",categoriaservice.listarC());
		modelAndView.addObject("producto",productoservice.listarP());
			
		return modelAndView;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editP(@PathVariable("id") String id) {
		
		ModelAndView modelAndView = new ModelAndView(Paginas.CRUDP);
		if(id!=null) {
			modelAndView.addObject("producto", productoservice.buscarP(id));
			modelAndView.addObject("marca",marcaservice.listarM());
			modelAndView.addObject("categoria",categoriaservice.listarC());
		}
		else {
			modelAndView.addObject("producto", new ProductoDTO());
			modelAndView.addObject("marca",marcaservice.listarM());
			modelAndView.addObject("categoria",categoriaservice.listarC());
		}
			
		return modelAndView;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView deleteP(@PathVariable("id") String id) {
		
		ModelAndView modelAndView = new ModelAndView(Paginas.LISTAP);
		if(id!=null) {
			productoservice.deleteP(id);

			modelAndView.addObject("marca",marcaservice.listarM());
			modelAndView.addObject("categoria",categoriaservice.listarC());
			modelAndView.addObject("producto",productoservice.listarP());
		}
		else {
			System.out.println("Error al eliminar la marca con codigo "+id);			

			modelAndView.addObject("marca",marcaservice.listarM());
			modelAndView.addObject("categoria",categoriaservice.listarC());
			modelAndView.addObject("producto",productoservice.listarP());
		}
			
		return modelAndView;
	}
	
	
}
