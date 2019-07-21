package br.com.jnsdev.financeiro.service;

import br.com.jnsdev.financeiro.datatables.Datatables;
import br.com.jnsdev.financeiro.datatables.DatatablesColunas;
import br.com.jnsdev.financeiro.domain.Categoria;
import br.com.jnsdev.financeiro.domain.Fornecedor;
import br.com.jnsdev.financeiro.repository.CategoriaRepository;
import br.com.jnsdev.financeiro.repository.LancamentoDespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Autowired
	private LancamentoDespesaRepository despesaRepository;

	@Autowired
	private Datatables datatables;

	@Transactional(readOnly = false)
	public void salvar(Categoria categoria) {
		repository.save(categoria);

	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarCategoria(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.CATEGORIAS);
		Page<?> pages = datatables.getSearch().isEmpty() 
				? repository.findAll(datatables.getPageable())
				: repository.findAllByNome(datatables.getSearch(), datatables.getPageable());

		return datatables.getResponse(pages);
	}

	@Transactional(readOnly = true)
	public Categoria buscaPorId(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly = true)
    public List<Categoria> buscarTodosOrderByNome() {
		return repository.findAllOrdeByNome();
    }

	@Transactional(readOnly = true)
    public Categoria buscarPorId(Long id) {
		return repository.getOne(id);
    }

	@Transactional(readOnly = false)
    public void delete(Long id) {
		repository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public boolean naoExisteCategoriaEmLancamentoDespesa(Long id) {
		return despesaRepository.hasCategoriaDepesasCadastrada(id).isEmpty();
	}
}
