package com.pastry.ecom.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class RestUtilitario {
	
	
	public <T> ResponseEntity consumeRestServicePUT(String endpoint,HttpEntity request, Class<T> responseType) {  
	    try {
	    	RestTemplate restCliente = new RestTemplate();
	        return restCliente.exchange(endpoint,HttpMethod.PUT,request, responseType);
	    } catch (RestClientResponseException e) {
	        return ResponseEntity
	            .status(e.getRawStatusCode())
	            .body(e.getResponseBodyAsString());
	    }
	    catch(Exception e){
	        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	    }
	}
	
	
	public <T> ResponseEntity consumeRestServicePOST(String endpoint,HttpEntity request, Class<T> responseType) {  
	    try {
	    	RestTemplate restCliente = new RestTemplate();
	        return restCliente.exchange(endpoint,HttpMethod.POST,request, responseType);
	    } catch (RestClientResponseException e) {
	        return ResponseEntity
	            .status(e.getRawStatusCode())
	            .body(e.getResponseBodyAsString());
	    }
	    catch(Exception e){
	        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	    }
	}
	
	
	public <T> ResponseEntity consumeRestServiceGET(String endpoint,HttpEntity request, Class<T> responseType) {  
	    try {
	    	RestTemplate restCliente = new RestTemplate();
	        return restCliente.exchange(endpoint,HttpMethod.GET,request, responseType);
	    } catch (RestClientResponseException e) {
	        return ResponseEntity
	            .status(e.getRawStatusCode())
	            .body(e.getResponseBodyAsString());
	    }
	    catch(Exception e){
	        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	    }
	}
	

}
