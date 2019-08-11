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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jnsdev.financeiro.domain.Categoria;
import br.com.jnsdev.financeiro.domain.Cliente;
import br.com.jnsdev.financeiro.domain.FormaPagamento;
import br.com.jnsdev.financeiro.domain.Fornecedor;
import br.com.jnsdev.financeiro.domain.LancamentoDespesa;
import br.com.jnsdev.financeiro.domain.LancamentoReceita;
import br.com.jnsdev.financeiro.domain.enuns.Pagamento;
import br.com.jnsdev.financeiro.service.CategoriaService;
import br.com.jnsdev.financeiro.service.ClienteService;
import br.com.jnsdev.financeiro.service.FormaPagamentoService;
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
	private FormaPagamentoService formaPagamentoService;

	// ******RECEITAS********

	@GetMapping("cadastrar/receita")
	public String abriCadastroReceita(LancamentoReceita lancamento, ModelMap model, RedirectAttributes attr,
			@AuthenticationPrincipal User user) {
		Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
		if (cliente.hasNotId()) {
			attr.addFlashAttribute("falha", "Cliente: " + user.getUsername()
					+ ", Você deve concluir seu cadastro antes realizar uma operação.");
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

	@PostMapping("editar/receita")
	public String editarReceita(LancamentoReceita lancamento, RedirectAttributes attr) {
		service.editarReceita(lancamento);
		attr.addFlashAttribute("sucesso", "Lançamento atualizado com sucesso");
		return "redirect:/lancamentos/receita/editar/" + lancamento.getId();

	}

	@GetMapping("/receita/editar/{id}")
	public String preEditarReceita(@PathVariable("id") Long id, ModelMap model) {
		LancamentoReceita lancamento = service.buscarLancamentoReceita(id).get();
		model.addAttribute("lancamento", lancamento);
		return "lancamento/cadastro-receita";
	}

	@GetMapping("receita/datatables/server/{mes}/{ano}")
	public ResponseEntity<?> getLancamentoReceita(HttpServletRequest request, @PathVariable int mes,
			@PathVariable int ano, @AuthenticationPrincipal User user) {
		Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
		return ResponseEntity.ok(service.buscarLancamentoReceita(request, mes, ano, cliente.getId()));
	}

	@GetMapping("/receita/excluir/{id}")
	public String excluirReceita(@PathVariable("id") Long id, RedirectAttributes attr) {

		LocalDate dtVencto = service.buscarDataVencimentoReceita(id);

		service.deleteReceita(id);

		return "redirect:/lancamentos/lista/" + dtVencto.getMonthValue() + "/" + dtVencto.getYear();
	}

	// ******DESPESAS********

	@GetMapping("cadastrar/despesa")
	public String abriCadastroDespesa(LancamentoDespesa lancamento, ModelMap model, RedirectAttributes attr,
			@AuthenticationPrincipal User user) {

		Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
		if (cliente.hasNotId()) {
			attr.addFlashAttribute("falha", "Cliente: " + user.getUsername()
					+ ", Você deve concluir seu cadastro antes realizar uma operação.");
			return "redirect:/clientes/dados";
		}

		if (formaPagamentoService.naoTemFormaDePagamentoCadastrada(cliente.getId())) {
			attr.addFlashAttribute("falha", "Usuario não tem forma de Pagamento Cadastrada!");

			return "redirect:/fp";
		}

		lancamento.setDtLancamento(LocalDate.now());
		lancamento.setGastoFixo(Boolean.FALSE);
		lancamento.setQtdParcelas(0);
		lancamento.setPagamento(Pagamento.AVISTA);
		lancamento.setCliente(cliente);
		model.addAttribute("lancamento", lancamento);

		return "lancamento/cadastro-despesa";
	}

	@GetMapping("/despesa/editar/{id}")
	public String preEditarDespesa(@PathVariable("id") Long id, ModelMap model) {
		LancamentoDespesa lancamento = service.buscarLancamentoDespesa(id).get();
		model.addAttribute("lancamento", lancamento);
		return "lancamento/editar-despesa";
	}

	@PostMapping("salvar/despesa")
	public String salvarDespesa(LancamentoDespesa lancamento, RedirectAttributes attr) {
		service.salvarDespesa(lancamento);
		attr.addFlashAttribute("sucesso", "Lançamento salvo com sucesso");
		return "redirect:/lancamentos/cadastrar/despesa";
	}

	@PostMapping("editar/despesa")
	public String editarDespesa(LancamentoDespesa lancamento, RedirectAttributes attr) {
		service.editarDespesa(lancamento);
		attr.addFlashAttribute("sucesso", "Lançamento atualizado com sucesso");
		return "redirect:/lancamentos/despesa/editar/" + lancamento.getId();
	}

	@GetMapping("despesa/datatables/server/{mes}/{ano}")
	public ResponseEntity<?> getLancamentoDespesaMonth(HttpServletRequest request, 
			@PathVariable("mes") int mes,
			@PathVariable("ano") int ano, 
			@AuthenticationPrincipal User user) {
		Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
		return ResponseEntity.ok(service.buscarLancamentoDespesas(request, mes, ano, cliente.getId()));
	}

	@GetMapping("/despesa/excluir/{id}")
	public String excluirDespesa(@PathVariable("id") Long id, RedirectAttributes attr) {

		LocalDate dtVencto = service.buscarDataVencimentoDespesa(id);

		service.deleteDespesa(id);

		return "redirect:/lancamentos/lista/" + dtVencto.getMonthValue() + "/" + dtVencto.getYear();
	}

	// ******LISTAS********

	/**
	 * Lista utilizada na exclusão de despesa para retornas para mesma pagina
	 * 
	 * @param model
	 * @param mes
	 * @param ano
	 * @return
	 */
	@GetMapping("lista")
	public String listaLancamentosMontYear(ModelMap model) {
		int mes = LocalDate.now().getMonth().getValue();
		int ano = LocalDate.now().getYear();

		model.addAttribute("mes", mes);
		model.addAttribute("ano", ano);
		return "lancamento/lista";
	}

	// ******ATRIBUTOS********

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
		return formaPagamentoService.buscarTodosPorUsuario(cliente.getId());
	}

	@ModelAttribute("pagamentos")
	public Pagamento[] getTiposGastos() {
		return Pagamento.values();
	}
}
