package br.com.jnsdev.financeiro.web.controller;

import br.com.jnsdev.financeiro.domain.FormaPagamento;
import br.com.jnsdev.financeiro.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("fp")
public class FormaPagamentoControler {
	
	@Autowired
	private FormaPagamentoService service;
	
	@GetMapping
	public String abrir() {
		return "fp/fpagamento";
	}
	
	@PostMapping("salvar")
	public String salvar(FormaPagamento fp) {
		return "";
	}

}
