package br.com.jnsdev.financeiro.web.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jnsdev.financeiro.domain.Cliente;
import br.com.jnsdev.financeiro.domain.Endereco;
import br.com.jnsdev.financeiro.domain.Usuario;
import br.com.jnsdev.financeiro.repository.filter.DclienteFilter;
import br.com.jnsdev.financeiro.service.ClienteService;
import br.com.jnsdev.financeiro.service.DashboardService;

@Controller
@RequestMapping("dashboard")
public class DashboardController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private DashboardService service;

	@GetMapping
	public String Dashboard(Cliente cliente, ModelMap model, RedirectAttributes attr,
			@AuthenticationPrincipal User user) {
		cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());

		int mes = LocalDate.now().getMonthValue();
		int ano = LocalDate.now().getYear();

		if (cliente.hasId()) {
			model.addAttribute("cliente", cliente);
			model.addAttribute("filter", new DclienteFilter());
//			Se for Cliente
			if (!clienteService.userHasAdmin(user)) {
				model.addAttribute("valores", service.getValoresMesUsuario(cliente.getId(), mes, ano));
			} else {// Se for Cliente

			}

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
	
	@GetMapping("filter")
	public ModelAndView pesquisar(DclienteFilter filter, @AuthenticationPrincipal User user) {
		ModelAndView mv = new ModelAndView("/dashboard/dashboard");
		Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
		mv.addObject("filter", new DclienteFilter());
		mv.addObject("cliente", cliente);
		mv.addObject("valores", service.getValoresMesUsuario(cliente.getId(), filter.getMes(), filter.getAno()));
		
		return mv;
	}

}
