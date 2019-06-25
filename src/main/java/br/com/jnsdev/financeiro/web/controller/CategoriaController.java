package br.com.jnsdev.financeiro.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jnsdev.financeiro.domain.Categoria;
import br.com.jnsdev.financeiro.service.CategoriaService;

@Controller
@RequestMapping("categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService service;

	@GetMapping
	public String abrir(Categoria categoria) {
		return "categoria/cadastro";
	}

	@PostMapping("salvar")
	public String salvar(Categoria categoria, RedirectAttributes attr) {
		service.salvar(categoria);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso");

		return "redirect:/categorias";
	}

	@GetMapping("datatables/server")
	public ResponseEntity<?> getEspecialidade(HttpServletRequest request) {

		return ResponseEntity.ok(service.buscarCategoria(request));
	}
	
	@GetMapping("editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("categoria", service.buscaPorId(id));
        return "categoria/cadastro";
    }

}
