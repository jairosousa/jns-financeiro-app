package br.com.jnsdev.financeiro.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jnsdev.financeiro.datatables.Datatables;
import br.com.jnsdev.financeiro.datatables.DatatablesColunas;
import br.com.jnsdev.financeiro.domain.Fornecedor;
import br.com.jnsdev.financeiro.domain.constante.Constante;
import br.com.jnsdev.financeiro.repository.FornecedoresRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedoresRepository repository;

	@Autowired
	private Datatables datatables;

	@Autowired
	private AtividadeService atividadeService;
	
	@Transactional(readOnly = false)
	public void salvar(Fornecedor fornecedor) {
		fornecedor.getTelefones().forEach(phone -> phone.setFornecedor(fornecedor));
		String acao = fornecedor.hasId() ? Constante.ATUALIZAR_FORNECEDOR : Constante.CADASTRO_FORNECEDOR;
		String titulo = fornecedor.hasId() ? ", atualizou o fornecedor: " : ", cadastrou o fornecedor: ";
		repository.save(fornecedor);
		atividadeService.salvarAtividade(acao, 
				", " + titulo + fornecedor.getNome());
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarFornecedor(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.FORNECEDORES);

		Page<?> pages = datatables.getSearch().isEmpty() ? repository.findAll(datatables.getPageable())
				: repository.findAllBySearchNome(datatables.getSearch(), datatables.getPageable());

		return datatables.getResponse(pages);
	}

	@Transactional(readOnly = true)
	public Fornecedor buscaPorId(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void deletar(Long id) {
		atividadeService.salvarAtividade(Constante.EXCLUSAO_FORNECEDOR, 
				", excluio o fornecedor com id: " + id);
		repository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Fornecedor> buscarTodos() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public List<Fornecedor> buscarTodosOrderByNome() {
		return repository.findAllOrdeByNome();
	}

	@Transactional(readOnly = true)
	public Fornecedor buscarPorId(Long id) {
		return repository.getOne(id);
	}

}
