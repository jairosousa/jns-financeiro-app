package br.com.jnsdev.financeiro.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.jnsdev.financeiro.domain.Cliente;
import br.com.jnsdev.financeiro.domain.Endereco;
import br.com.jnsdev.financeiro.domain.Usuario;
import br.com.jnsdev.financeiro.service.ClienteService;

@Controller
@RequestMapping("dashboard")
public class DashboardController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public String Dashboard(Cliente cliente, ModelMap model, @AuthenticationPrincipal User user) {
		cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
		if (cliente.hasId() || clienteService.userHasAdmin(user)) {
			return "dashboard/dashboard";
		} else {
			cliente.setEndereco(new Endereco());
			cliente.setUsuario(new Usuario(user.getUsername()));

		}
		model.addAttribute("cliente", cliente);
		return "cliente/cadastro";

	}

}
