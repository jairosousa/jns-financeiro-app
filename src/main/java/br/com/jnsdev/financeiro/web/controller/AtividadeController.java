package br.com.jnsdev.financeiro.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.jnsdev.financeiro.domain.Categoria;
import br.com.jnsdev.financeiro.service.AtividadeService;

@Controller
@RequestMapping("atividades")
public class AtividadeController {
	
	@Autowired
	private AtividadeService atividadeService;
	
	@GetMapping
	public String abrir(Categoria categoria) {
		return "atividade/atividades";
	}
	
	@GetMapping("datatables/server")
	public ResponseEntity<?> getEspecialidade(HttpServletRequest request) {

		return ResponseEntity.ok(atividadeService.buscarAtividades(request));
	}

}
