package br.com.jnsdev.financeiro.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jnsdev.financeiro.datatables.Datatables;
import br.com.jnsdev.financeiro.datatables.DatatablesColunas;
import br.com.jnsdev.financeiro.domain.Fornecedor;
import br.com.jnsdev.financeiro.repository.FornecedoresRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedoresRepository repository;

	@Autowired
	private Datatables datatables;

	@Transactional(readOnly = false)
	public void salvar(Fornecedor fornecedor) {
		repository.save(fornecedor);
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
		repository.deleteById(id);
	}

}
