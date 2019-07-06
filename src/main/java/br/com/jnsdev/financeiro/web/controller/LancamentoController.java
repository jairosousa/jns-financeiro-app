package br.com.jnsdev.financeiro.web.controller;

import br.com.jnsdev.financeiro.domain.Categoria;
import br.com.jnsdev.financeiro.domain.Fornecedor;
import br.com.jnsdev.financeiro.domain.Lancamento;
import br.com.jnsdev.financeiro.service.CategoriaService;
import br.com.jnsdev.financeiro.service.FornecedorService;
import br.com.jnsdev.financeiro.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("cadastrar")
    public String abriCadastro(Lancamento lancamento) {


        return "lancamento/cadastro";
    }


    @ModelAttribute("fornecedores")
    public List<Fornecedor> getFornecedores() {
        return fornecedorService.buscarTodosOrderByNome();
    }

    @ModelAttribute("categorias")
    public List<Categoria> getCategoriasa() {
        return categoriaService.buscarTodosOrderByNome();
    }
}
