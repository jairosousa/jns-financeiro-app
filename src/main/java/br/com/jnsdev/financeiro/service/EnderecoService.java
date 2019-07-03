package br.com.jnsdev.financeiro.service;

import br.com.jnsdev.financeiro.service.projection.EnderecoTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EnderecoService {

	private static String URLCEP = "http://viacep.com.br/ws/{cep}/json/";

	public EnderecoTO buscarEndereco(String cep) throws JsonParseException, JsonMappingException, IOException {

		cep = cep.replace(".", "").replace("-", "");

		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);

		RestTemplate restTemplate = new RestTemplate();

		EnderecoTO enderecoTO = restTemplate.getForObject(URLCEP, EnderecoTO.class, params);

		System.out.println("endereco: " + enderecoTO.toString());

		return enderecoTO;

	}

}
