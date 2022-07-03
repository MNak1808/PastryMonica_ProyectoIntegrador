package com.pastry.ecom.controller;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pastry.ecom.dto.DetalleOrdenDTO;
import com.pastry.ecom.dto.OrdenDTO;
import com.pastry.ecom.dto.ProductoDTO;
import com.pastry.ecom.dto.UsuarioDTO;
import com.pastry.ecom.serviceImpl.DetalleOrdenServiceImpl;
import com.pastry.ecom.serviceImpl.OrdenServiceImpl;
import com.pastry.ecom.serviceImpl.ProductoServiceImpl;
import com.pastry.ecom.serviceImpl.UsuarioServiceImpl;
import com.pastry.ecom.util.Paginas;

@Controller
@RequestMapping("/compra")
public class CompraController extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler{
	
	
	
	@Autowired
	@Qualifier("productoservice")
	private ProductoServiceImpl productoservice;
	
	@Autowired
	private UsuarioServiceImpl usuarioservice;
	
	@Autowired
	private OrdenServiceImpl ordenservice;
	
	@Autowired
	private DetalleOrdenServiceImpl detalleservice;
	
	List<DetalleOrdenDTO> detalles = new ArrayList<DetalleOrdenDTO>();
	
	OrdenDTO orden=new OrdenDTO();
	
	
	public String generarNumeroOrden(){
        int numero = 0;
        String numeroConcatenado = "";
        
        List<OrdenDTO> ordenes = ordenservice.findAll();
        //pasar datos de la bd, String a integer
        List<Integer> numeros = new ArrayList<>();
        //Pasamos el N. de orden(cadena) a enteros
        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));
        //Validacion list<Ordenes> es nulo
        if(ordenes.isEmpty()){
            numero = 1;
        }else{
            // Obtenemos el numero mayor de la lista
            numero = numeros.stream().max(Integer::compare).get();
            numero++;
        }
        
        if(numero < 10){ //000000000(1)
            numeroConcatenado = "000000000" + String.valueOf(numero);
        }else if(numero < 100){
            numeroConcatenado = "00000000" + String.valueOf(numero);
        }else if(numero < 1000){
            numeroConcatenado = "0000000" + String.valueOf(numero);
        }else if(numero < 10000){
            numeroConcatenado = "000000" + String.valueOf(numero);
        }
        
        return numeroConcatenado;
    }
	
	
	@GetMapping("/cerrar")
	public String cerrarSesion( HttpSession session ) {
		session.removeAttribute("idusuario");
		List<DetalleOrdenDTO> ordenesNueva = new ArrayList<DetalleOrdenDTO>();
		detalles=ordenesNueva;
		orden=new OrdenDTO();
		return "redirect:/login";
	}
	
	@GetMapping(value="/list")
	public ModelAndView catalogo (HttpSession session){
		ModelAndView modelAndView=new ModelAndView(Paginas.CATALOGO);
		modelAndView.addObject("producto",productoservice.listarP());
		modelAndView.addObject("session",session.getAttribute("idusuario"));
		return modelAndView;
	}
	
	@GetMapping(value="/detalle/{id}")
	public ModelAndView verdetalle(@PathVariable("id") String id,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView(Paginas.DETALLEP);		
		DetalleOrdenDTO detalleOrden = new DetalleOrdenDTO();
        ProductoDTO ProductoC = productoservice.buscarP(id);
       
        detalleOrden.setPrecio(ProductoC.getPrecio_prod());
        detalleOrden.setNombre(ProductoC.getDescrip_prod());
        detalleOrden.setProducto_id(ProductoC.getCod_prod());
        
        modelAndView.addObject("detalle",detalleOrden);
        modelAndView.addObject("sesion", session.getAttribute("idusuario"));
		return modelAndView;
	}
	
	
	@PostMapping(value="/agregar")
	public ModelAndView añadircarrito(DetalleOrdenDTO detalle,HttpSession session) {
		String nomU=session.getAttribute("idusuario").toString();
		if(nomU!=null) {
			System.out.println(detalle);
			ModelAndView modelAndView = new ModelAndView(Paginas.CARRITO);
			DetalleOrdenDTO detalleOrden = new DetalleOrdenDTO();
			double total=detalle.getPrecio()*detalle.getCantidad();
			double totalRound=Math.round(total*100.0)/100.0;
			double sumaTotal = 0;
	        detalleOrden.setNombre(detalle.getNombre());   
	        detalleOrden.setPrecio(detalle.getPrecio());
	        detalleOrden.setCantidad(detalle.getCantidad());
	        detalleOrden.setTotal(totalRound);
	        detalleOrden.setProducto_id(detalle.getProducto_id());
	        detalles.add(detalleOrden);
	        
	        Long idProducto = detalle.getProducto_id();
	        boolean ingresado = detalles.stream().anyMatch(d -> d.getProducto_id() == idProducto);	       
	        if(!ingresado) {
	        	detalles.add(detalleOrden);
	        }
	        
	        
	        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
	        double sumatotalRound=Math.round(sumaTotal*100.0)/100.0;
	        orden.setTotal(sumatotalRound);
	        
	        modelAndView.addObject("cart",detalles);
	        modelAndView.addObject("orden", orden);	        
	        modelAndView.addObject("sesion", session.getAttribute("idusuario"));
	        
			return modelAndView;
		}
		else {
			ModelAndView modelAndView = new ModelAndView(Paginas.LOGIN);
			return modelAndView;
		}
	}
	
	@GetMapping("/cart")
	public ModelAndView getCart(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView(Paginas.CARRITO);
		modelAndView.addObject("cart",detalles);
		modelAndView.addObject("orden",orden);
		modelAndView.addObject("sesion",session.getAttribute("idusuario"));
		return modelAndView;
	}
	
	// Quitar los productos del carrito
    @GetMapping("/delete/cart/{id}")
    public ModelAndView deleteProductoCart(@PathVariable String id, HttpSession session){
        
        List<DetalleOrdenDTO> ordenesNueva = new ArrayList<DetalleOrdenDTO>();
        
        Long id2=Long.parseLong(id);
        
        for(DetalleOrdenDTO detalleOrden: detalles){
            if(detalleOrden.getProducto_id()!=id2){
                ordenesNueva.add(detalleOrden);
            }
        }
        // poner la nueva lista con los productos restantes
        detalles = ordenesNueva;
        System.out.println(detalles);
        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
        double sumatotalRound=Math.round(sumaTotal*100.0)/100.0;
        orden.setTotal(sumatotalRound);
        ModelAndView modelAndView = new ModelAndView(Paginas.CARRITO);
        modelAndView.addObject("cart",detalles);
		modelAndView.addObject("orden",orden);
        
		modelAndView.addObject("sesion",session.getAttribute("idusuario"));
        
        return modelAndView;
    }
	
    @GetMapping("/order")
    public ModelAndView order( HttpSession session){
        
        UsuarioDTO usuario = usuarioservice.buscarU(session.getAttribute("idusuario").toString());
        System.out.println(usuario);
        ModelAndView modelAndView = new ModelAndView(Paginas.RESUMEN);
        modelAndView.addObject("cart",detalles);
		modelAndView.addObject("orden",orden);
		modelAndView.addObject("usuario",usuario);
        
        return modelAndView;
    }
	
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session){
    	ZonedDateTime zonedDateTime = ZonedDateTime.now();
    	Instant instant = zonedDateTime.toInstant();
    	Date date = Date.from(instant);
        orden.setFecha(date);
        orden.setNumero(generarNumeroOrden());
        
        //Usuario
        UsuarioDTO usuario=usuarioservice.buscarU(session.getAttribute("idusuario").toString());
        
        // Guardamos los datos de la orden
        orden.setUsuarioid(Integer.parseInt(session.getAttribute("idusuario").toString()));
        ordenservice.save(orden);
        //Guardamos los datos de los detalles
        for(DetalleOrdenDTO dt:detalles){
        	int ordenid=ordenservice.findAll().size();
            dt.setOrden_id(ordenid);
            detalleservice.save(dt);
        }
        
        // Limpieza de la lista y orden
        orden = new OrdenDTO();
        detalles.clear();
       
        return "redirect:/compra/list";
    }	

}
