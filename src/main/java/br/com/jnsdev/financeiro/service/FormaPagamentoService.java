package br.com.jnsdev.financeiro.service;

import br.com.jnsdev.financeiro.datatables.Datatables;
import br.com.jnsdev.financeiro.datatables.DatatablesColunas;
import br.com.jnsdev.financeiro.domain.FormaPagamento;
import br.com.jnsdev.financeiro.domain.constante.Constante;
import br.com.jnsdev.financeiro.repository.FormaPagamentoRepository;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import br.com.jnsdev.financeiro.repository.LancamentoDespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository repository;

	@Autowired
	private Datatables datatables;

	@Autowired
	private LancamentoDespesaRepository despesaRepository;

	@Autowired
	private AtividadeService atividadeService;

	@Transactional(readOnly = false)
	public void salvar(FormaPagamento fp, String email) {
		repository.save(fp);
		atividadeService.salvarAtividade(Constante.CADASTRO_FORMA_PAGAMENTO, ", cadastrou uma forma de pagamento");
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarFormasPagamento(HttpServletRequest request, Long id) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.FORMAS_PAGAMENTOS);
		Page<?> pages = datatables.getSearch().isEmpty() ? repository.findAllById(id, datatables.getPageable())
				: repository.findAllByNomeById(id, datatables.getSearch(), datatables.getPageable());

		return datatables.getResponse(pages);
	}

	@Transactional(readOnly = true)
	public FormaPagamento buscaPorId(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void editar(FormaPagamento fp, String email) {
		FormaPagamento fFp = buscaPorId(fp.getId());
		fFp.setNome(fp.getNome());

		atividadeService.salvarAtividade(Constante.ATUALIZAR_FORMA_PAGAMENTO, ", atualizou uma forma de pagamento");
	}

	@Transactional(readOnly = false)
	public void delete(Long id, String email) {
		try {
			repository.deleteById(id);
			atividadeService.salvarAtividade(Constante.EXCLUSAO_FORMA_PAGAMENTO, ", excluiu uma forma de pagamento");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possivel excluir forma ce pagamento");
		}
	}

	@Transactional(readOnly = true)
	public boolean naoExisteFormaPagamentoEmLancamentoDespesa(Long id) {
		return despesaRepository.hasFormaPagamentoDepesasCadastrada(id).isEmpty();
	}

	@Transactional(readOnly = true)
	public List<FormaPagamento> buscarTodosPorUsuario(Long idCliente) {
		return repository.findAllByIdCliente(idCliente);
	}

	@Transactional(readOnly = true)
	public boolean naoTemFormaDePagamentoCadastrada(Long id) {
		return buscarTodosPorUsuario(id).isEmpty();
	}

}
