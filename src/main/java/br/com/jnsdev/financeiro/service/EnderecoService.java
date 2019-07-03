package br.com.jnsdev.financeiro.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.jnsdev.financeiro.service.projection.EnderecoTO;


@Service
public class EnderecoService {
	
	private final static String URLCEP= "http://viacep.com.br/ws/"; 
	
	public EnderecoTO buscarEndereco(String cep) throws JsonParseException, JsonMappingException, IOException {
		
		RestTemplate restTemplate = new RestTemplate();
		
		EnderecoTO enderecoTO = restTemplate.getForObject(URLCEP + cep + "/json/", EnderecoTO.class);
		
		System.out.println("endereco: " + enderecoTO.toString());
		
		return enderecoTO;
		
	}

}
