package br.com.jnsdev.financeiro.web.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.jnsdev.financeiro.domain.Cliente;
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

    @GetMapping("listar/{mes}/{ano}")
    public String listarDespesasAvencerNoMes(Model model, @PathVariable("mes") int mes, @PathVariable("ano") int ano,
            @AuthenticationPrincipal User user) {

        mes = (mes == 0) ? LocalDate.now().getMonthValue() : mes;
        ano = (ano == 0) ? LocalDate.now().getYear() : ano;

        Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
        model.addAttribute("mes", mes);
        model.addAttribute("ano", ano);
        model.addAttribute("despesas", service.buscarDespesasNãoPagasNoMes(cliente.getId(), mes, ano));

        return "pagamento/lista";
    }

}