package br.com.jnsdev.financeiro.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jnsdev.financeiro.datatables.Datatables;
import br.com.jnsdev.financeiro.datatables.DatatablesColunas;
import br.com.jnsdev.financeiro.domain.Lancamento;
import br.com.jnsdev.financeiro.domain.LancamentoDespesa;
import br.com.jnsdev.financeiro.domain.LancamentoReceita;
import br.com.jnsdev.financeiro.domain.enuns.Pagamento;
import br.com.jnsdev.financeiro.repository.LancamentoDespesaRepository;
import br.com.jnsdev.financeiro.repository.LancamentoReceitaRepository;
import br.com.jnsdev.financeiro.repository.LancamentoRepository;
import br.com.jnsdev.financeiro.repository.projection.LancamentoReceitaDTO;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository repository;

	@Autowired
	private LancamentoDespesaService lancamentoDespesasService;

	@Autowired
	private LancamentoReceitaRepository receitaRepository;

	@Autowired
	private LancamentoDespesaRepository despesaRepository;

	@Autowired
	private Datatables datatables;

	@Transactional(readOnly = false)
	public void salvar(Lancamento lancamento) {
		repository.save(lancamento);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarLancamentoReceita(HttpServletRequest request, Long id) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.LANCAMENTOS_RECEITA);

		Page<LancamentoReceitaDTO> pages = datatables.getSearch().isEmpty()
				? receitaRepository.findAllByIdCliente(id, datatables.getPageable())
				: receitaRepository.findAllBySearchByIdCliente(id, datatables.getSearch(), datatables.getPageable());

		return datatables.getResponse(pages);
	}

	@Transactional(readOnly = false)
	public void salvarReceita(LancamentoReceita lancamento) {
		receitaRepository.save(lancamento);
	}

	@Transactional(readOnly = false)
	public void salvarDespesa(LancamentoDespesa lancamento) {

		if (lancamento.getPagamento().equals(Pagamento.APRAZO)) {
			lancamentoDespesasService.gerarLancamentoParcelado(lancamento);
		} else {
			
			despesaRepository.save(lancamento);
		}
		
	}

}
