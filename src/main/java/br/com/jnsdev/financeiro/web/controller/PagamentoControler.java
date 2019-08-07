package br.com.jnsdev.financeiro.web.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.jnsdev.financeiro.domain.Cliente;
import br.com.jnsdev.financeiro.domain.LancamentoDespesa;
import br.com.jnsdev.financeiro.service.ClienteService;
import br.com.jnsdev.financeiro.service.PagamentoService;

/**
 * PagamentoControler
 */
@Controller
@RequestMapping("pagamentos")
public class PagamentoControler {

    @Autowired
    private PagamentoService service;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("listar")
    public String listarDespesasAvencerNoMes(Model model,
            @AuthenticationPrincipal User user) {

        int mes = LocalDate.now().getMonthValue();
        int ano = LocalDate.now().getYear();

        Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
        model.addAttribute("mes", mes);
        model.addAttribute("ano", ano);
        model.addAttribute("despesas", service.buscarDespesasNãoPagasNoMes(cliente.getId(), mes, ano));

        return "pagamento/lista";
    }

    @GetMapping("listar/ajax")
    public String listarDespesasAvencerNoMesAjax(@RequestParam(name = "mes", defaultValue = "0") int mes,
         @RequestParam(name = "ano", defaultValue = "0") int ano,
         ModelMap model,
        @AuthenticationPrincipal User user) {

        mes = (mes == 0) ? LocalDate.now().getMonthValue() : mes;
        ano = (ano == 0) ? LocalDate.now().getYear() : ano;

        Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
        model.addAttribute("mes", mes);
        model.addAttribute("ano", ano);
        model.addAttribute("despesas", service.buscarDespesasNãoPagasNoMes(cliente.getId(), mes, ano));

        return "pagamento/despesas-card";
    }
    
    @GetMapping("despesa/edit/{id}")
    public ResponseEntity<?> preEditarDespesas(@PathVariable("id") LancamentoDespesa despesa) {

        return ResponseEntity.ok(despesa);
    }
    
    @PostMapping("edit/despesa")
    public String editarPromocao(LancamentoDespesa despesa) {
        service.atualizarDespesaDataPagamento(despesa);
        return "redirect:/pagamentos/listar";
    }

}