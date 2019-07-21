package br.com.jnsdev.financeiro.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model,
                            RedirectAttributes attr, @AuthenticationPrincipal User user) {
        Cliente cliente = clienteService.buscarPorUsuarioEmail(user.getUsername());
        FormaPagamento fp = service.buscaPorId(id);
        fp.setCliente(cliente);
        model.addAttribute("fp", fp);
        return "fp/fpagamento";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {

        if (service.naoExisteFormaPagamentoEmLancamentoDespesa(id)) {
            service.delete(id);
            attr.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        } else {
            attr.addFlashAttribute("falha", "Operação não realizada, existe despesa relacionadas a forma de pagamento");
        }

        return "redirect:/fp";
    }

}
