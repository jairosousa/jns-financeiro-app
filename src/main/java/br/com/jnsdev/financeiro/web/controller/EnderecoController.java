package br.com.jnsdev.financeiro.web.controller;

import br.com.jnsdev.financeiro.service.EnderecoService;
import br.com.jnsdev.financeiro.service.projection.EnderecoTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService service;
	
	@GetMapping("cep/{cep}")
	public ResponseEntity<EnderecoTO> getEnderecoByCep(@PathVariable String cep) throws JsonParseException, JsonMappingException, IOException {
        return  new ResponseEntity<EnderecoTO>(service.buscarEndereco(cep), HttpStatus.OK);
    }

}
