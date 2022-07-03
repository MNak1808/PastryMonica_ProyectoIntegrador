package com.pastry.ecom.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pastry.ecom.dto.OrdenDTO;
import com.pastry.ecom.dto.ProductoDTO;
import com.pastry.ecom.util.RestUtilitario;

@Service
public class OrdenServiceImpl implements OrdenService{

	@Override
	public List<OrdenDTO> findAll() {
		String endpoint = "http://localhost:8080/apiO/all";
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<OrdenDTO[]> respuesta=null;
		
		RestUtilitario restUtil =new RestUtilitario();
		
		respuesta=restUtil.consumeRestServiceGET(endpoint, request, OrdenDTO[].class);
		
		if(respuesta.getStatusCodeValue()==HttpStatus.OK.value()) {
			OrdenDTO[] orden=respuesta.getBody();
			List<OrdenDTO>listO=new ArrayList<OrdenDTO>();
			for(OrdenDTO o: orden) {
				listO.add(o);
			}
			return listO;
		}
		else return new ArrayList<OrdenDTO>();
	}

	@Override
	public OrdenDTO findById(String id) {
		String endpoint = "http://localhost:8080/apiO/find/"+id;
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<OrdenDTO> respuesta = null;
		
		RestUtilitario restUtil = new RestUtilitario();
		
		respuesta = restUtil.consumeRestServiceGET(
				endpoint,
				request, 
				OrdenDTO.class);
		
		if(respuesta.getStatusCodeValue()== HttpStatus.OK.value()) {
			OrdenDTO orden =respuesta.getBody();
			return orden;
		}
		else return null;
	}

	@Override
	public boolean save(OrdenDTO orden) {
		String endpoint="http://localhost:8080/apiO/save";
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(orden,headers);
		
		ResponseEntity<?> respuesta=null;
		
		RestUtilitario restUtil=new RestUtilitario();
		
		respuesta=restUtil.consumeRestServicePOST(endpoint, request, null);
		
		if(respuesta.getStatusCodeValue()==HttpStatus.OK.value()) {
			return true;
		}else return false;
	}

	@Override
	public List<OrdenDTO> findByUsuarioid(String id) {
		String endpoint = "http://localhost:8080/apiO/findU/"+id;
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<OrdenDTO[]> respuesta = null;
		
		RestUtilitario restUtil = new RestUtilitario();
		
		respuesta=restUtil.consumeRestServiceGET(endpoint, request, OrdenDTO[].class);
		
		if(respuesta.getStatusCodeValue()==HttpStatus.OK.value()) {
			OrdenDTO[] orden=respuesta.getBody();
			List<OrdenDTO>listO=new ArrayList<OrdenDTO>();
			for(OrdenDTO o: orden) {
				listO.add(o);
			}
			return listO;
		}
		else return new ArrayList<OrdenDTO>();
	}
	
	

}
