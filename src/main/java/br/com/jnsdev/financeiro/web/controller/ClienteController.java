package br.com.jnsdev.financeiro.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.jnsdev.financeiro.domain.Cliente;
import br.com.jnsdev.financeiro.domain.Endereco;
import br.com.jnsdev.financeiro.domain.Usuario;
import br.com.jnsdev.financeiro.service.ClienteService;
import br.com.jnsdev.financeiro.service.UsuarioService;

@Controller
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@Autowired
	private UsuarioService usuarioService;

	// abrir pagina de dados pessoais do paciente
	@GetMapping("/dados")
	public String cadastrar(Cliente cliente, ModelMap model, @AuthenticationPrincipal User user) {
		cliente = service.buscarPorUsuarioEmail(user.getUsername());
		if (cliente.hasNotId()) {
			cliente.setEndereco(new Endereco());
			cliente.setUsuario(new Usuario(user.getUsername()));
		}
		model.addAttribute("cliente", cliente);
		return "cliente/cadastro";
	}
	
	// salvar o form de dados pessoais do paciente com verificacao de senha
		@PostMapping("/salvar")
		public String salvar(Cliente cliente, ModelMap model, @AuthenticationPrincipal User user) {
			Usuario u = usuarioService.buscarPorEmail(user.getUsername());
			if (UsuarioService.isSenhaCorreta(cliente.getUsuario().getSenha(), u.getSenha())) {
				cliente.setUsuario(u);
				service.salvar(cliente);
				model.addAttribute("sucesso", "Seus dados foram inseridos com sucesso.");
			} else {
				model.addAttribute("falha", "Sua senha não confere, tente novamente.");
			}
			return "cliente/cadastro";
		}
		
		// editar o form de dados pessoais do paciente com verificacao de senha
		@PostMapping("/editar")
		public String editar(Cliente cliente, ModelMap model, @AuthenticationPrincipal User user) {
			Usuario u = usuarioService.buscarPorEmail(user.getUsername());
			if (UsuarioService.isSenhaCorreta(cliente.getUsuario().getSenha(), u.getSenha())) {
				service.editar(cliente);
				model.addAttribute("sucesso", "Seus dados foram editados com sucesso.");
			} else {
				model.addAttribute("falha", "Sua senha não confere, tente novamente.");
			}
			return "cliente/cadastro";
		}	

}
