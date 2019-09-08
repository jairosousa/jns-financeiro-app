package br.com.jnsdev.financeiro.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final Logger LOG = LoggerFactory.getLogger(AtividadeService.class);

	@Autowired
	private AtividadeRepository repository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private Datatables datatables;
	
	public void salvarAtividade(String acao, String titulo) {
		Long id = null;
		Usuario usuario = usuarioService.getUsuarioLogado();
		Cliente cliente = clienteService.getClienteLogado();
		try {
			if (usuario.hasId() && cliente.hasId()) {
				id = salvarAtividadeComSessao(acao, titulo, cliente, usuario).getId();
			} else {
				id = salvarAtividadesSemSession(acao, titulo, usuario).getId();
			}
			LOG.info("SALVO UMA ATIVIDADE ID: {}", id);
		}catch (Exception e){
			LOG.error("ERRO! EM SALVAR ATIVIDADE", e);
		}
	}

	@Transactional(readOnly = false)
	private Atividade salvarAtividadeComSessao(String acao, String titulo, Cliente cliente, Usuario usuario) {
		return repository.save(new Atividade(acao, cliente.getNome() + titulo, usuario));
	}

	@Transactional(readOnly = false)
	private Atividade salvarAtividadesSemSession(String acao, String titulo, Usuario usuario) {
		return repository.save(new Atividade(acao, usuario.getEmail() + titulo,usuario));
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
