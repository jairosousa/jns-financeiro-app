package br.com.jnsdev.financeiro.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jnsdev.financeiro.domain.Perfil;
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
        return new ModelAndView("usuario/alterar", "usuario", service.buscarPorId(id));
    }
    
    /**
     * Salvar usuario por Administrador 
     *
     * @param usuario
     * @param attr
     * @return
     */
    @PostMapping("cadastro/salvar/adm")
    public String salvarUsuarios(Usuario usuario, RedirectAttributes attr) {
        List<Perfil> perfis = usuario.getPerfis();
        if (perfis.size() == 0) {
            attr.addFlashAttribute("falha", "Usuário deve ter pelo menos um perfil");
            attr.addFlashAttribute("usuario", usuario);
        } else {
            try {
                service.salvarUsuarioAdm(usuario);
                attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");

            } catch (DataIntegrityViolationException e) {
                attr.addFlashAttribute("falha", "Falha! Cadastro não realizado, email já existente!");

            }
        }
        return "redirect:/u/novo/cadastro/usuario";
    }

}
