package br.com.jnsdev.financeiro.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jnsdev.financeiro.datatables.Datatables;
import br.com.jnsdev.financeiro.datatables.DatatablesColunas;
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
	
	@Autowired
	private Datatables datatables;
	
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
	
	@Transactional(readOnly = true)
	public Map<String, Object> buscarAtividades(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.ATIVIDADES);
		Page<?> pages = datatables.getSearch().isEmpty() 
				? repository.findAll(datatables.getPageable())
				: repository.findAllByNome(datatables.getSearch(), datatables.getPageable());

		return datatables.getResponse(pages);
	}

}
