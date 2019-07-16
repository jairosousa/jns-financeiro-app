package br.com.jnsdev.financeiro.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jnsdev.financeiro.domain.Cliente;
import br.com.jnsdev.financeiro.domain.FormaPagamento;
import br.com.jnsdev.financeiro.service.ClienteService;
import br.com.jnsdev.financeiro.service.FormaPagamentoService;

@Controller
@RequestMapping("fp")
public class FormaPagamentoControler {
	
	@Autowired
	private FormaPagamentoService service;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public String abrir(FormaPagamento fp, ModelMap model, 
			RedirectAttributes attr, @AuthenticationPrincipal User user) {
		Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
		fp.setCliente(cliente);
		model.addAttribute("fp", fp);
		
		if (cliente.hasNotId()) {
			attr.addFlashAttribute("falha", "Cliente: " + user.getUsername() + ", Você deve concluir seu cadastro antes realizar uma operação.");
			return "redirect:/clientes/dados";
		}
		
		return "fp/fpagamento";
	}
	
	@PostMapping("salvar")
	public String salvar(FormaPagamento fp, RedirectAttributes attr) {
		service.salvar(fp);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso");
		
		return "redirect:/fp";

	}
	
	@PostMapping("editar")
	public String editar(FormaPagamento fp, RedirectAttributes attr) {
		service.editar(fp);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso");
		
		return "redirect:/fp";

	}
	
	@GetMapping("datatables/server")
	public ResponseEntity<?> getEspecialidade(HttpServletRequest request, @AuthenticationPrincipal User user) {
		Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
		return ResponseEntity.ok(service.buscarFormasPagamento(request, cliente.getId()));
	}
	
	@GetMapping("editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("fp", service.buscaPorId(id));
        return "fp/fpagamento";
    }

}
