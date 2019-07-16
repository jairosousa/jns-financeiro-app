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
import br.com.jnsdev.financeiro.domain.Fornecedor;
import br.com.jnsdev.financeiro.domain.Telefone;
import br.com.jnsdev.financeiro.service.ClienteService;
import br.com.jnsdev.financeiro.service.FornecedorService;

@Controller
@RequestMapping("fornecedores")
public class FornecedorController {
	
	@Autowired
	private FornecedorService service;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public String abrir(Fornecedor fornecedor, RedirectAttributes attr, @AuthenticationPrincipal User user) {
		fornecedor.getTelefones().add(new Telefone(fornecedor));
		if (clienteService.userHasAdmin(user)) {
			return "fornecedor/cadastro";
		} else {
			Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
			if (cliente.hasNotId()) {
				attr.addFlashAttribute("falha", "Cliente: " + user.getUsername() + ", Você deve concluir seu cadastro antes realizar uma operação.");
				return "redirect:/clientes/dados";
			}
			return "fornecedor/cadastro";
		}
	}
	
	@PostMapping("salvar")
	public String salvar(Fornecedor fornecedor, RedirectAttributes attr) {
		service.salvar(fornecedor);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso");
		
		return  "redirect:/fornecedores";
	}
	
	@GetMapping("lista")
    public String listaFornecedores() {
        return "fornecedor/lista";
    }
	
	@GetMapping("datatables/server")
	public ResponseEntity<?> getEspecialidade(HttpServletRequest request) {

		return ResponseEntity.ok(service.buscarFornecedor(request));
	}
	
	@GetMapping("editar/{id}")
	public String editar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("fornecedor", service.buscaPorId(id));
        return "fornecedor/cadastro";
	}
	
	@GetMapping("excluir/{id}")
	public String excluir(@PathVariable("id") Long id) {
		
		service.deletar(id);
		
        return "redirect:/fornecedores/lista";
	}
	

}
