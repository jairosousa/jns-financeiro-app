package br.com.jnsdev.financeiro.web.controller;

import br.com.jnsdev.financeiro.domain.*;
import br.com.jnsdev.financeiro.domain.enuns.TipoLancamento;
import br.com.jnsdev.financeiro.service.CategoriaService;
import br.com.jnsdev.financeiro.service.ClienteService;
import br.com.jnsdev.financeiro.service.FornecedorService;
import br.com.jnsdev.financeiro.service.LancamentoService;
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

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("cadastrar/receita")
    public String abriCadastroReceita(LancamentoReceita lancamento, ModelMap model, @AuthenticationPrincipal User user) {
        Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
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

    @PostMapping("salvar")
    public String salvar(Lancamento lancamento, RedirectAttributes attr) {
        service.salvar(lancamento);
        attr.addFlashAttribute("sucesso", "Lançamento salvo com sucesso");
        return "redirect:/lancamentos/cadastrar";
    }

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

    @ModelAttribute("tiposlancamentos")
    public TipoLancamento[] getTiposLancamentos() {
        return TipoLancamento.values();
    }
}
