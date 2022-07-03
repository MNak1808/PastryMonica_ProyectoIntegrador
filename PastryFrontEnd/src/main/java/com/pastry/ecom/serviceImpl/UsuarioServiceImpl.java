package com.pastry.ecom.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.pastry.ecom.dto.UsuarioDTO;
import com.pastry.ecom.util.RestUtilitario;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Override
	public List<UsuarioDTO> listarU() {
		String endpoint = "http://localhost:8080/apiU/all";
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<UsuarioDTO[]> respuesta=null;
		
		RestUtilitario restUtil =new RestUtilitario();
		
		respuesta=restUtil.consumeRestServiceGET(endpoint, request, UsuarioDTO[].class);
		
		if(respuesta.getStatusCodeValue()==HttpStatus.OK.value()) {
			UsuarioDTO[] usuario=respuesta.getBody();
			List<UsuarioDTO>listU=new ArrayList<UsuarioDTO>();
			for(UsuarioDTO u:usuario) {
				listU.add(u);
			}
			return listU;
		}
		else return new ArrayList<UsuarioDTO>();
	}

	@Override
	public UsuarioDTO buscarU(String id) {
		String endpoint = "http://localhost:8080/apiU/find/"+id;
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<UsuarioDTO> respuesta = null;
		
		RestUtilitario restUtil = new RestUtilitario();
		
		respuesta = restUtil.consumeRestServiceGET(
				endpoint,
				request, 
				UsuarioDTO.class);
		
		if(respuesta.getStatusCodeValue()== HttpStatus.OK.value()) {
			UsuarioDTO usuario =respuesta.getBody();
			return usuario;
		}
		else return null;
	}

	@Override
	public UsuarioDTO buscarByUsuario(String usuario) {
		String endpoint = "http://localhost:8080/apiU/findU/"+usuario;
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<UsuarioDTO> respuesta = null;
		
		RestUtilitario restUtil = new RestUtilitario();
		
		respuesta = restUtil.consumeRestServiceGET(
				endpoint,
				request, 
				UsuarioDTO.class);
		
		if(respuesta.getStatusCodeValue()== HttpStatus.OK.value()) {
			UsuarioDTO user =respuesta.getBody();
			return user;
		}
		else return null;
	}

	@Override
	public boolean guardarU(UsuarioDTO usuario) {
		String endpoint="http://localhost:8080/apiU/save";
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(usuario,headers);
		
		ResponseEntity<?> respuesta=null;
		
		RestUtilitario restUtil=new RestUtilitario();
		
		respuesta=restUtil.consumeRestServicePOST(endpoint, request, null);
		
		if(respuesta.getStatusCodeValue()==HttpStatus.OK.value()) {
			return true;
		}else return false;
	}

	@Override
	public boolean deleteU(String id) {
		String endpoint = "http://localhost:8080/apiU/delete/"+id;
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<UsuarioDTO> respuesta = null;
		
		RestUtilitario restUtil = new RestUtilitario();
		
		respuesta = restUtil.consumeRestServicePOST(
				endpoint,
				request, 
				UsuarioDTO.class);
		
		if(respuesta.getStatusCodeValue()== HttpStatus.OK.value()) {
			return true;
		}
		else return false;
	}

}
