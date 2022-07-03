package com.pastry.ecom.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pastry.ecom.dto.CategoriaDTO;
import com.pastry.ecom.dto.MarcaDTO;
import com.pastry.ecom.dto.ProductoDTO;
import com.pastry.ecom.util.RestUtilitario;

@Service("productoservice")
public class ProductoServiceImpl implements ProductoService{
	
	
	
	@Override
	public List<ProductoDTO> listarP() {
		String endpoint = "http://localhost:8080/apiP/all";
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<ProductoDTO[]> respuesta=null;
		
		RestUtilitario restUtil =new RestUtilitario();
		
		respuesta=restUtil.consumeRestServiceGET(endpoint, request, ProductoDTO[].class);
		
		if(respuesta.getStatusCodeValue()==HttpStatus.OK.value()) {
			ProductoDTO[] producto=respuesta.getBody();
			List<ProductoDTO>listP=new ArrayList<ProductoDTO>();
			for(ProductoDTO p: producto) {
				listP.add(p);
			}
			return listP;
		}
		else return new ArrayList<ProductoDTO>();
	}

	@Override
	public ProductoDTO buscarP(String id) {
		String endpoint = "http://localhost:8080/apiP/find/"+id;
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<ProductoDTO> respuesta = null;
		
		RestUtilitario restUtil = new RestUtilitario();
		
		respuesta = restUtil.consumeRestServiceGET(
				endpoint,
				request, 
				ProductoDTO.class);
		
		if(respuesta.getStatusCodeValue()== HttpStatus.OK.value()) {
			ProductoDTO producto =respuesta.getBody();
			return producto;
		}
		else return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean guardarP(ProductoDTO producto) {
		String endpoint="http://localhost:8080/apiP/save";
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(producto,headers);
		
		ResponseEntity<?> respuesta=null;
		
		RestUtilitario restUtil=new RestUtilitario();
		
		respuesta=restUtil.consumeRestServicePOST(endpoint, request, null);
		
		if(respuesta.getStatusCodeValue()==HttpStatus.OK.value()) {
			return true;
		}else return false;
	}

	@Override
	public boolean deleteP(String id) {
		String endpoint = "http://localhost:8080/apiP/delete/"+id;
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<ProductoDTO> respuesta = null;
		
		RestUtilitario restUtil = new RestUtilitario();
		
		respuesta = restUtil.consumeRestServicePOST(
				endpoint,
				request, 
				ProductoDTO.class);
		
		if(respuesta.getStatusCodeValue()== HttpStatus.OK.value()) {
			return true;
		}
		else return false;
	}
	
}
