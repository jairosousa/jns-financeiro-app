package br.com.jnsdev.financeiro.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import br.com.jnsdev.financeiro.datatables.Datatables;
import br.com.jnsdev.financeiro.datatables.DatatablesColunas;
import br.com.jnsdev.financeiro.domain.Cliente;
import br.com.jnsdev.financeiro.domain.Perfil;
import br.com.jnsdev.financeiro.domain.Usuario;
import br.com.jnsdev.financeiro.domain.constante.Constante;
import br.com.jnsdev.financeiro.domain.enuns.PerfilTipo;
import br.com.jnsdev.financeiro.exception.AcessoNegadoException;
import br.com.jnsdev.financeiro.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private Datatables datatables;

	@Autowired
	private EmailService emailService;

	@Autowired
	private AtividadeService atividadeService;

	public static boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
		return new BCryptPasswordEncoder().matches(senhaDigitada, senhaArmazenada);
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = buscarPorEmailEAtivo(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario: " + username + " não encontrado."));

		return new User(usuario.getEmail(), usuario.getSenha(),
				AuthorityUtils.createAuthorityList(getAuthorities(usuario.getPerfis())));
	}

	private String[] getAuthorities(List<Perfil> perfis) {
		String[] authorities = new String[perfis.size()];
		for (int i = 0; i < perfis.size(); i++) {
			authorities[i] = perfis.get(i).getDesc();
		}
		return authorities;
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarTodos(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.USUARIOS);
		Page<Usuario> pages = datatables.getSearch().isEmpty() ? repository.findAll(datatables.getPageable())
				: repository.findByEmailOrPerfil(datatables.getSearch(), datatables.getPageable());

		return datatables.getResponse(pages);
	}

	/**
	 * Salva novo usuario
	 * 
	 * @param usuario
	 * @throws MessagingException
	 */
	@Transactional(readOnly = false)
	public void salvarNovoUsuario(Usuario usuario) throws MessagingException {

		String crypt = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(crypt);
		usuario.addPerfil(PerfilTipo.USUARIO);

		repository.save(usuario);

		emailDeConfirmacaoDeCadastro(usuario.getEmail());

		atividadeService.salvarAtividade(Constante.CADASTRO_NOVO_USUARIO, ", cadastrou-se no sistema");

	}

	private void emailDeConfirmacaoDeCadastro(String email) throws MessagingException {
		String codigo = Base64Utils.encodeToString(email.getBytes());
		emailService.enviarPedidoDeConfirmacaoDeCadastro(email, codigo);
	}

	/**
	 * Atualiza o usuario pelo administrador, Perfis e ativo
	 * 
	 * @param usuario
	 */
	@Transactional(readOnly = false)
	public void salvarUsuarioAdm(Usuario usuario) {
		Usuario user = repository.findById(usuario.getId()).get();
		user.setAtivo(usuario.isAtivo());
		user.setPerfis(usuario.getPerfis());
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorId(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorIdEPerfis(Long usuarioId, Long[] perfisId) {
		return repository.findByIdAndPerfis(usuarioId, perfisId)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario inexistente!"));
	}

	@Transactional(readOnly = false)
	public void alterarSenha(Usuario usuario, String senha) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		repository.save(usuario);

		atividadeService.salvarAtividade(Constante.USUARIO_ATUALIZOU_SENHA, ", alterou a sua senha");
	}

	@Transactional(readOnly = false)
	public void ativarCadastroUsuarioCliente(String codigo) {
		String email = new String(Base64Utils.decodeFromString(codigo));
		Usuario usuario = buscarPorEmail(email);
		if (usuario.hasNotId()) {
			throw new AcessoNegadoException(
					"Não foi possível ativar seu cadastro. Entre em contato com o suporte.");
		}
		usuario.setAtivo(true);
		atividadeService.salvarAtividade(Constante.USUARIO_ATIVADO, ", foi ativado");

	}

	@Transactional(readOnly = false)
	public void pedidoRedefinicaoDeSenha(String email) throws MessagingException {
		Usuario usuario = buscarPorEmailEAtivo(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario " + email + " não encontrado."));
		;

		String verificador = RandomStringUtils.randomAlphanumeric(6);

		usuario.setCodigoVerificador(verificador);

		emailService.enviarPedidoRedefinicaoSenha(email, verificador);

		atividadeService.salvarAtividade(Constante.USUARIO_PEDIDO_REDEFIR_SENHA, ", solicitou a redefinição de senha.");
	}

	@Transactional(readOnly = true)
	public Optional<Usuario> buscarPorEmailEAtivo(String email) {
		return repository.findByEmailAndAtivo(email);
	}
	
	public Usuario getUsuarioLogado() {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		return buscarPorEmail(username);
	}
}
