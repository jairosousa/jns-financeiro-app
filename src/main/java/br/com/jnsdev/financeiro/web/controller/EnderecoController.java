package br.com.jnsdev.financeiro.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.jnsdev.financeiro.service.EnderecoService;
import br.com.jnsdev.financeiro.service.projection.EnderecoTO;

@Controller
@RequestMapping("enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService service;
	
	@GetMapping("cep/{cep}")
	public ResponseEntity<EnderecoTO> getEnderecoByCep(@PathVariable String cep) throws JsonParseException, JsonMappingException, IOException {
        System.out.println("CEP: " + cep);
        return  new ResponseEntity<EnderecoTO>(service.buscarEndereco(cep), HttpStatus.OK);
    }

}
