package br.com.jnsdev.financeiro.web.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jnsdev.financeiro.domain.Categoria;
import br.com.jnsdev.financeiro.domain.Cliente;
import br.com.jnsdev.financeiro.domain.FormaPagamento;
import br.com.jnsdev.financeiro.domain.Fornecedor;
import br.com.jnsdev.financeiro.domain.LancamentoDespesa;
import br.com.jnsdev.financeiro.domain.LancamentoReceita;
import br.com.jnsdev.financeiro.service.CategoriaService;
import br.com.jnsdev.financeiro.service.ClienteService;
import br.com.jnsdev.financeiro.service.FormasPagamentoService;
import br.com.jnsdev.financeiro.service.FornecedorService;
import br.com.jnsdev.financeiro.service.LancamentoService;

@Controller
@RequestMapping("lancamentos")
public class LancamentoController {

	@Autowired
	private LancamentoService service;

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private FormasPagamentoService formasPagamentoService;

	@GetMapping("cadastrar/receita")
	public String abriCadastroReceita(LancamentoReceita lancamento, ModelMap model,
			RedirectAttributes attr, @AuthenticationPrincipal User user) {
		Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
		if (cliente.hasNotId()) {
			attr.addFlashAttribute("falha", "Cliente: " + user.getUsername() + ", Você deve concluir seu cadastro antes realizar uma operação.");
			return "redirect:/clientes/dados";
		}
		lancamento.setCliente(cliente);
		model.addAttribute("lancamento", lancamento);

		return "lancamento/cadastro-receita";
	}

	@PostMapping("salvar/receita")
	public String salvarReceita(LancamentoReceita lancamento, RedirectAttributes attr) {
		lancamento.setDtLancamento(LocalDate.now());
		service.salvarReceita(lancamento);
		attr.addFlashAttribute("sucesso", "Lançamento salvo com sucesso");
		return "redirect:/lancamentos/cadastrar/receita";
	}

	@GetMapping("cadastrar/despesa")
	public String abriCadastroDespesa(LancamentoDespesa lancamento, ModelMap model,
			RedirectAttributes attr, @AuthenticationPrincipal User user) {
		Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
		
		if (cliente.hasNotId()) {
			attr.addFlashAttribute("falha", "Cliente: " + user.getUsername() + ", Você deve concluir seu cadastro antes realizar uma operação.");
			return "redirect:/clientes/dados";
		}
		
		if(formasPagamentoService.temFormaDePagamentos(cliente.getId())) {
			attr.addFlashAttribute("falha", "Usuario não tem forma de Pagamento Cadastrada!");
			
			return "redirect:/fp";
		}
				
		lancamento.setDtLancamento(LocalDate.now());
		lancamento.setParcelado(Boolean.FALSE);
		lancamento.setQtdParcelas(1);
		lancamento.setCliente(cliente);
		model.addAttribute("lancamento", lancamento);

		return "lancamento/cadastro-despesa";
	}

	@PostMapping("salvar/despesa")
	public String salvarDespesa(LancamentoDespesa lancamento, RedirectAttributes attr) {
		service.salvarDespesa(lancamento);
		attr.addFlashAttribute("sucesso", "Lançamento salvo com sucesso");
		return "redirect:/lancamentos/cadastrar/despesa";
	}

//    @PostMapping("salvar")
//    public String salvar(Lancamento lancamento, RedirectAttributes attr) {
//        service.salvar(lancamento);
//        attr.addFlashAttribute("sucesso", "Lançamento salvo com sucesso");
//        return "redirect:/lancamentos/cadastrar";
//    }

	@GetMapping("datatables/server")
	public ResponseEntity<?> getLancamento(HttpServletRequest request, @AuthenticationPrincipal User user) {
		Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
		return ResponseEntity.ok(service.buscarLancamento(request, cliente.getId()));
	}

	@GetMapping("lista")
	public String listaLancamentos() {
		return "lancamento/lista";
	}

	@ModelAttribute("fornecedores")
	public List<Fornecedor> getFornecedores() {
		return fornecedorService.buscarTodosOrderByNome();
	}

	@ModelAttribute("categorias")
	public List<Categoria> getCategorias() {
		return categoriaService.buscarTodosOrderByNome();
	}

	@ModelAttribute("formasPagamentos")
	public List<FormaPagamento> getFormasPagamentos() {
		Cliente cliente = clienteService.getUsuarioLogado();
		return formasPagamentoService.buscarTodosPorUsuario(cliente.getId());
	}

//    @ModelAttribute("tiposlancamentos")
//    public TipoLancamento[] getTiposLancamentos() {
//        return TipoLancamento.values();
//    }
}