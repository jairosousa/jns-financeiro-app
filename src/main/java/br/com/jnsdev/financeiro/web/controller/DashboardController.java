package br.com.jnsdev.financeiro.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String Dashboard(Cliente cliente, ModelMap model, RedirectAttributes attr,
			@AuthenticationPrincipal User user) {
		cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
		if (cliente.hasId() || clienteService.userHasAdmin(user)) {
			model.addAttribute("cliente", cliente);
			return "dashboard/dashboard";
		} else {
			cliente.setEndereco(new Endereco());
			cliente.setUsuario(new Usuario(user.getUsername()));

		}
		attr.addFlashAttribute("falha", "Usuario: " + user.getUsername()
				+ " você deve completar seu cadastro antes de executar qualquer operação!");
		model.addAttribute("cliente", cliente);
		return "redirect:/clientes/dados";

	}

}
