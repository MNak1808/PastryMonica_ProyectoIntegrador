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
import com.pastry.ecom.util.RestUtilitario;

@Service("categoriaservice")
public class CategoriaServiceImpl implements CategoriaService{
	
	
	
	@Override
	public List<CategoriaDTO> listarC() {
		String endpoint = "http://localhost:8080/apiC/all";
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<CategoriaDTO[]> respuesta=null;
		
		RestUtilitario restUtil =new RestUtilitario();
		
		respuesta=restUtil.consumeRestServiceGET(endpoint, request, CategoriaDTO[].class);
		
		if(respuesta.getStatusCodeValue()==HttpStatus.OK.value()) {
			CategoriaDTO[] categoria=respuesta.getBody();
			List<CategoriaDTO>listC=new ArrayList<CategoriaDTO>();
			for(CategoriaDTO c:categoria) {
				listC.add(c);
			}
			return listC;
		}
		else return new ArrayList<CategoriaDTO>();
	}

	@Override
	public CategoriaDTO buscarC(String id) {
		String endpoint = "http://localhost:8080/apiC/find/"+id;
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<CategoriaDTO> respuesta = null;
		
		RestUtilitario restUtil = new RestUtilitario();
		
		respuesta = restUtil.consumeRestServiceGET(
				endpoint,
				request, 
				CategoriaDTO.class);
		
		if(respuesta.getStatusCodeValue()== HttpStatus.OK.value()) {
			CategoriaDTO categoria =respuesta.getBody();
			return categoria;
		}
		else return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean guardarC(CategoriaDTO categoria) {
		String endpoint="http://localhost:8080/apiC/save";
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(categoria,headers);
		
		ResponseEntity<?> respuesta=null;
		
		RestUtilitario restUtil=new RestUtilitario();
		
		respuesta=restUtil.consumeRestServicePOST(endpoint, request, null);
		
		if(respuesta.getStatusCodeValue()==HttpStatus.OK.value()) {
			return true;
		}else return false;
	}

	@Override
	public boolean deleteC(String id) {
		String endpoint = "http://localhost:8080/apiC/delete/"+id;
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<CategoriaDTO> respuesta = null;
		
		RestUtilitario restUtil = new RestUtilitario();
		
		respuesta = restUtil.consumeRestServicePOST(
				endpoint,
				request, 
				CategoriaDTO.class);
		
		if(respuesta.getStatusCodeValue()== HttpStatus.OK.value()) {
			return true;
		}
		else return false;
	}
	
}
