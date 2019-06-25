package br.com.jnsdev.financeiro.web.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({ "/", "/home" })
	public String home() {
		return "home";
	}

	// Abrir a pagina de login
	@GetMapping("login")
	public String login() {
		return "login";
	}

	// Login invalido
	@GetMapping({ "login-error" })
	public String loginError(ModelMap model) {
		model.addAttribute("alerta", "erro");
		model.addAttribute("titulo", "Credenciais inválidas");
		model.addAttribute("texto", "Login ou senha incorretos, tente novamente");
		model.addAttribute("subtexto", "Acesso permitido apenas para cadastros já ativado.");
		return "login";
	}

	// Acesso Negado
	@GetMapping({ "acesso-negado" })
	public String acessoNegado(ModelMap model, HttpServletResponse response) {
		model.addAttribute("status", response.getStatus());
		model.addAttribute("error", "Acesso Negado");
		model.addAttribute("message", "Você não tem permissão para essa area ou ação");
		return "error";
	}

	@GetMapping("datatables/translationBR")
    public ResponseEntity<?> getDatatablesTranslatio() {
		Map<String, Object> oPaginate = new LinkedHashMap<>();
		oPaginate.put("sNext", "Próximo");
		oPaginate.put("sPrevious", "Anterior");
		oPaginate.put("sFirst", "Primeiro");
		oPaginate.put("sLast", "Último");
		
		Map<String, Object> oAria = new LinkedHashMap<>();
			oAria.put("sSortAscending", "Ordenar colunas de forma ascendente");
			oAria.put("sSortDescending", "Ordenar colunas de forma descendente");
		
    	Map<String, Object> json = new LinkedHashMap<>();
    	json.put("sEmptyTable", "Nenhum registro encontrado");
    	json.put("sInfo", "Mostrando de _START_ até _END_ de _TOTAL_ registros");
    	json.put("sInfoEmpty", "Mostrando 0 até 0 de 0 registros");
    	json.put("sInfoFiltered", "(Filtrados de _MAX_ registros)");
    	json.put("sInfoPostFix", "");
    	json.put("sInfoThousands", ".");
    	json.put("sLengthMenu", "_MENU_ resultados por página");
    	json.put("sLoadingRecords", "Carregando...");
    	json.put("sProcessing", "Processando...");
    	json.put("sZeroRecords", "Nenhum registro encontrado");
		json.put("sSearch", "Pesquisar");
		json.put("oPaginate", oPaginate);
		json.put("oAria", oAria);

		return ResponseEntity.ok(json);
	}
}
