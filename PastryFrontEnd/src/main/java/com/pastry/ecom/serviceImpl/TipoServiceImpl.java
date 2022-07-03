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
import com.pastry.ecom.dto.TipoDTO;
import com.pastry.ecom.util.RestUtilitario;

@Service("tiposervice")
public class TipoServiceImpl implements TipoService{
	
	@Override
	public List<TipoDTO> listarT() {
		String endpoint = "http://localhost:8080/apiT/all";
		
		RestTemplate restCliente = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		
		ResponseEntity<TipoDTO[]> respuesta=null;
		
		RestUtilitario restUtil =new RestUtilitario();
		
		respuesta=restUtil.consumeRestServiceGET(endpoint, request, TipoDTO[].class);
		
		if(respuesta.getStatusCodeValue()==HttpStatus.OK.value()) {
			TipoDTO[] tipo=respuesta.getBody();
			List<TipoDTO>listT=new ArrayList<TipoDTO>();
			for(TipoDTO t:tipo) {
				listT.add(t);
			}
			return listT;
		}
		else return new ArrayList<TipoDTO>();
	}

	
	
}
