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
import com.pastry.ecom.util.RestUtilitario;

@Service("marcaservice")
public class MarcaServiceImpl implements MarcaService{
	
	
	
	@Override
	public List<MarcaDTO> listarM() {
		String endpoint = "http://localhost:8080/apiM/all";
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<MarcaDTO[]> respuesta=null;
		
		RestUtilitario restUtil =new RestUtilitario();
		
		respuesta=restUtil.consumeRestServiceGET(endpoint, request, MarcaDTO[].class);
		
		if(respuesta.getStatusCodeValue()==HttpStatus.OK.value()) {
			MarcaDTO[] marca=respuesta.getBody();
			List<MarcaDTO>listM=new ArrayList<MarcaDTO>();
			for(MarcaDTO m: marca) {
				listM.add(m);
			}
			return listM;
		}
		else return new ArrayList<MarcaDTO>();
	}

	@Override
	public MarcaDTO buscarM(String id) {
		String endpoint = "http://localhost:8080/apiM/find/"+id;
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<MarcaDTO> respuesta = null;
		
		RestUtilitario restUtil = new RestUtilitario();
		
		respuesta = restUtil.consumeRestServiceGET(
				endpoint,
				request, 
				MarcaDTO.class);
		
		if(respuesta.getStatusCodeValue()== HttpStatus.OK.value()) {
			MarcaDTO marca =respuesta.getBody();
			return marca;
		}
		else return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean guardarM(MarcaDTO marca) {
		String endpoint="http://localhost:8080/apiM/save";
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(marca,headers);
		
		ResponseEntity<?> respuesta=null;
		
		RestUtilitario restUtil=new RestUtilitario();
		
		respuesta=restUtil.consumeRestServicePOST(endpoint, request, null);
		
		if(respuesta.getStatusCodeValue()==HttpStatus.OK.value()) {
			return true;
		}else return false;
	}

	@Override
	public boolean deleteM(String id) {
		String endpoint = "http://localhost:8080/apiM/delete/"+id;
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<MarcaDTO> respuesta = null;
		
		RestUtilitario restUtil = new RestUtilitario();
		
		respuesta = restUtil.consumeRestServicePOST(
				endpoint,
				request, 
				MarcaDTO.class);
		
		if(respuesta.getStatusCodeValue()== HttpStatus.OK.value()) {
			return true;
		}
		else return false;
	}
	
}
