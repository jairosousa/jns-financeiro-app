package br.com.jnsdev.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jnsdev.financeiro.domain.Atividade;
import br.com.jnsdev.financeiro.domain.Cliente;
import br.com.jnsdev.financeiro.domain.Usuario;
import br.com.jnsdev.financeiro.repository.AtividadeRepository;

@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository repository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private UsuarioService usuarioService;
	
	public void salvarAtividade(String acao, String titulo) {
		
		Usuario usuario = usuarioService.getUsuarioLogado();
		Cliente cliente = clienteService.getClienteLogado();
		
		if (usuario.hasId() && cliente.hasId()) {
			salvarAtividadeComSessao(acao, titulo, cliente, usuario);
		} else {
			salvarAtividadesSemSession(acao, titulo, usuario);
		}
	}

	@Transactional(readOnly = false)
	private void salvarAtividadeComSessao(String acao, String titulo, Cliente cliente, Usuario usuario) {

		Atividade atividade = new Atividade(acao, cliente.getNome() + titulo, usuario);
		
		repository.save(atividade);
	}

	@Transactional(readOnly = false)
	private void salvarAtividadesSemSession(String acao, String titulo, Usuario usuario) {
		
		Atividade atividade = new Atividade(acao, usuario.getEmail() + titulo,usuario);
		
		repository.save(atividade);
		
	}

}
