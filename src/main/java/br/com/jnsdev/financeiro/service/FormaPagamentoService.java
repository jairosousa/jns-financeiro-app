package br.com.jnsdev.financeiro.service;

import br.com.jnsdev.financeiro.datatables.Datatables;
import br.com.jnsdev.financeiro.datatables.DatatablesColunas;
import br.com.jnsdev.financeiro.domain.FormaPagamento;
import br.com.jnsdev.financeiro.repository.FormaPagamentoRepository;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository repository;

	@Autowired
	private Datatables datatables;

	@Transactional(readOnly = false)
	public void salvar(FormaPagamento fp) {
		repository.save(fp);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarFormasPagamento(HttpServletRequest request, Long id) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.FP);
		Page<?> pages = datatables.getSearch().isEmpty() ? repository.findAllById(id, datatables.getPageable())
				: repository.findAllByNomeById(id, datatables.getSearch(), datatables.getPageable());

		return datatables.getResponse(pages);
	}

	@Transactional(readOnly = true)
	public FormaPagamento buscaPorId(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void editar(FormaPagamento fp) {
		FormaPagamento fFp = buscaPorId(fp.getId());
		fFp.setNome(fp.getNome());
	}

}
