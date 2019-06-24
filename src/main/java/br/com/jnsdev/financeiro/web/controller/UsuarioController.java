package br.com.jnsdev.financeiro.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.jnsdev.financeiro.domain.Usuario;
import br.com.jnsdev.financeiro.service.UsuarioService;

@Controller
@RequestMapping("u")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

//  Abrir cadastro de usuários
	@GetMapping("novo/cadastro/usuario")
	public String cadastroPorAdministradorUsuario(Usuario usuario) {
		return "usuario/cadastro";
	}

	@GetMapping("lista")
	public String listaUsuarios() {
		return "usuario/lista";
	}
	
	@GetMapping("/datatables/server/usuarios")
    public ResponseEntity<?> listarUsuariosDatatables(HttpServletRequest request) {
        return ResponseEntity.ok(service.buscarTodos(request));
    }
	
	// Pre Editar usuário de credenciais
    @GetMapping("editar/credenciais/usuario/{id}")
    public ModelAndView preEditarCredenciais(@PathVariable("id") Long id) {
        return new ModelAndView("usuario/cadastro", "usuario", service.buscarPorId(id));
    }

}
