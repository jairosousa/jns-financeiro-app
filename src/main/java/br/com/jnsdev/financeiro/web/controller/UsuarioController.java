package br.com.jnsdev.financeiro.web.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jnsdev.financeiro.domain.Perfil;
import br.com.jnsdev.financeiro.domain.Usuario;
import br.com.jnsdev.financeiro.service.UsuarioService;

@Controller
@RequestMapping("/u")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

//  Abrir cadastro de usuários
	@GetMapping("cadastro/usuario/novo")
	public String cadastroPorAdministradorUsuario(Usuario usuario) {
		return "usuario/cadastrar-se";
	}

	// pagina de resposta do cadatro de paciente
	@GetMapping("/cadastro/realizado")
	public String cadastroRealizado() {

		return "fragments/mensagem";
	}

	/**
	 * Salva novo Usuario Recebe o form da página cadastra-se
	 * 
	 * @param usuario
	 * @param attr
	 * @return
	 * @throws MessagingException
	 */
	@PostMapping("cadastro/usuario/novo")
	public String salvarUsuariosNovos(Usuario usuario, BindingResult result) throws MessagingException {

		try {
			service.salvarNovoUsuario(usuario);

		} catch (DataIntegrityViolationException e) {
			result.reject("email", "Ops... Este e-mail já existe na base de dados.");
			return "cadastrar-se";

		}
		return "redirect:/u/cadastro/realizado";
	}

	// recebe a requisicao de confirmacao de cadastro
	@GetMapping("/confirmacao/cadastro")
	public String respostaConfirmacaoCadastroPaciente(@RequestParam("codigo") String codigo, RedirectAttributes attr) {
		service.ativarCadastroUsuarioCliente(codigo);
		attr.addFlashAttribute("alerta", "sucesso");
		attr.addFlashAttribute("titulo", "Cadastro Ativado!");
		attr.addFlashAttribute("texto", "Parabéns, seu cadastro está ativo.");
		attr.addFlashAttribute("subtexto", "Singa com seu login/senha");
		return "redirect:/login";
	}

	/**
	 * Abre a tela de listagem
	 * 
	 * @return
	 */
	@GetMapping("lista")
	public String listaUsuarios() {
		return "usuario/lista";
	}

	/**
	 * Busca ao datatables de usuarios
	 * 
	 * @param request
	 * @return
	 */
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
	 * Salvar usuario por Administrador (Atualiza o status e o perfil)
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
//        return "redirect:/u/novo/cadastro/usuario";
		return "redirect:/u/lista";
	}

	// abre a pagina de pedido de redefinicao de senha
	@GetMapping("/p/redefinir/senha")
	public String pedidoRedefinirSenha() {

		return "usuario/pedido-recuperar-senha";
	}

	// form de pedido de recuperar senha
	@GetMapping("/p/recuperar/senha")
	public String redefinirSenha(String email, ModelMap model) throws MessagingException {
		service.pedidoRedefinicaoDeSenha(email);
		model.addAttribute("sucesso",
				"Em instantes você reberá um e-mail para " + "prosseguir com a redefinição de sua senha.");
		model.addAttribute("usuario", new Usuario(email));
		return "usuario/recuperar-senha";
	}

	// salvar a nova senha via recuperacao de senha
	@PostMapping("/p/nova/senha")
	public String confirmacaoDeRedefinicaoDeSenha(Usuario usuario, ModelMap model) {
		Usuario u = service.buscarPorEmail(usuario.getEmail());
		if (!usuario.getCodigoVerificador().equals(u.getCodigoVerificador())) {
			model.addAttribute("falha", "Código verificador não confere.");
			return "usuario/recuperar-senha";
		}
		u.setCodigoVerificador(null);
		service.alterarSenha(u, usuario.getSenha());
		model.addAttribute("alerta", "sucesso");
		model.addAttribute("titulo", "Senha redefinida!");
		model.addAttribute("texto", "Você já pode logar no sistema.");
		return "login";
	}
	
	
	/**
	 * Abrir o formulario de troca de senha pelo Usuário
	 * @return
	 */
	@GetMapping("editar/senha")
    public String abrirEditarSenha() {
    	return "usuario/editar-senha";
    }
	
	@PostMapping("/confirmar/senha")
    public String editarSenha(@RequestParam("senha1") String s1, @RequestParam("senha2") String s2, 
    						  @RequestParam("senha3") String s3, @AuthenticationPrincipal User user,
    						  RedirectAttributes attr) {
    	
    	if (!s1.equals(s2)) {
    		attr.addFlashAttribute("falha", "Senhas não conferem, tente novamente");
    		return "redirect:/u/editar/senha";
    	}
    	
    	Usuario u = service.buscarPorEmail(user.getUsername());
    	if(!UsuarioService.isSenhaCorreta(s3, u.getSenha())) {
    		attr.addFlashAttribute("falha", "Senha atual não confere, tente novamente");
    		return "redirect:/u/editar/senha";
    	}
    		
    	service.alterarSenha(u, s1);
    	attr.addFlashAttribute("sucesso", "Senha alterada com sucesso.");
    	return "redirect:/u/editar/senha";
    }

}
