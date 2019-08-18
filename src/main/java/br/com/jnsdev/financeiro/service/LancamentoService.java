package br.com.jnsdev.financeiro.service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jnsdev.financeiro.datatables.Datatables;
import br.com.jnsdev.financeiro.datatables.DatatablesColunas;
import br.com.jnsdev.financeiro.domain.LancamentoDespesa;
import br.com.jnsdev.financeiro.domain.LancamentoReceita;
import br.com.jnsdev.financeiro.domain.constante.Constante;
import br.com.jnsdev.financeiro.domain.enuns.Pagamento;
import br.com.jnsdev.financeiro.repository.LancamentoDespesaRepository;
import br.com.jnsdev.financeiro.repository.LancamentoReceitaRepository;
import br.com.jnsdev.financeiro.repository.projection.LancamentoDespesaDTO;
import br.com.jnsdev.financeiro.repository.projection.LancamentoReceitaDTO;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoDespesaService despesasService;

	@Autowired
	private LancamentoReceitaRepository receitaRepository;

	@Autowired
	private LancamentoDespesaRepository despesaRepository;

	@Autowired
	private Datatables datatables;
	
	@Autowired
	private AtividadeService atividadeService;

	@Transactional(readOnly = true)
	public Map<String, Object> buscarLancamentoReceita(HttpServletRequest request, int mes, int ano, Long id) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.LANCAMENTOS_RECEITA);

		mes = (mes == 0) ? LocalDate.now().getMonthValue() : mes;
		ano = (ano == 0) ? LocalDate.now().getYear() : ano;

		Page<LancamentoReceitaDTO> pages = datatables.getSearch().isEmpty()
				? receitaRepository.findAllByIdClienteByMonth(id, mes, ano, datatables.getPageable())
				: receitaRepository.findAllBySearchByIdClienteByMonth(id, mes, ano, datatables.getSearch(),
						datatables.getPageable());

		return datatables.getResponse(pages);
	}

	@Transactional(readOnly = false)
	public void salvarReceita(LancamentoReceita lancamento) {
		receitaRepository.save(lancamento);
		atividadeService.salvarAtividade(Constante.ATUALIZACAO_RECEITA,
				", cadastrou uma receita");
	}

	@Transactional(readOnly = false)
	public void editarReceita(LancamentoReceita lancamento) {
		LancamentoReceita lr = buscarLancamentoReceita(lancamento.getId()).get();

		lr.setCliente(lancamento.getCliente());
		lr.setFornecedor(lancamento.getFornecedor());
		lr.setNome(lancamento.getNome());
		lr.setDescricao(lancamento.getDescricao());
		lr.setDtLancamento(lancamento.getDtLancamento());
		lr.setDtRecebimento(lancamento.getDtRecebimento());
		lr.setValor(lancamento.getValor());
		
		atividadeService.salvarAtividade(Constante.ATUALIZACAO_RECEITA,
				", atualizou uma receita");
	}

	@Transactional(readOnly = true)
	public Optional<LancamentoReceita> buscarLancamentoReceita(Long id) {
		return receitaRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public void deleteReceita(Long id) {
		receitaRepository.deleteById(id);
		atividadeService.salvarAtividade(Constante.EXCLUSAO_RECEITA,
				", excluio uma receita com id: " + id);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarLancamentoDespesas(HttpServletRequest request, int mes, int ano, Long id) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.LANCAMENTOS_DESPESA);

		mes = (mes == 0) ? LocalDate.now().getMonthValue() : mes;
		ano = (ano == 0) ? LocalDate.now().getYear() : ano;

		Page<LancamentoDespesaDTO> pages = datatables.getSearch().isEmpty()
				? despesaRepository.findAllByIdClienteByMonth(id, mes, ano, datatables.getPageable())
				: despesaRepository.findAllBySearchByIdClienteByMonth(id, mes, ano, (datatables.getSearch()),
						datatables.getPageable());
				
		return datatables.getResponse(pages);
	}

	@Transactional(readOnly = false)
	public void salvarDespesa(LancamentoDespesa lancamento) {

		if (lancamento.getPagamento().equals(Pagamento.APRAZO)) {
			despesasService.gerarLancamentoParcelado(lancamento);
		} else {
			despesasService.gerarLancamentoAvista(lancamento);
		}

	}

	@Transactional(readOnly = false)
	public void editarDespesa(LancamentoDespesa lancamento) {
		LancamentoDespesa ld = buscarLancamentoDespesa(lancamento.getId()).get();

		ld.setCliente(lancamento.getCliente());
		ld.setDescricao(lancamento.getDescricao());
		ld.setNome(lancamento.getNome());
		ld.setDtLancamento(lancamento.getDtLancamento());
		ld.setFornecedor(lancamento.getFornecedor());
		ld.setValor(lancamento.getValor());
		ld.setGastoFixo(lancamento.isGastoFixo());
		ld.setPagamento(lancamento.getPagamento());
		ld.setCategoria(lancamento.getCategoria());
		ld.setFormaPagamento(lancamento.getFormaPagamento());
		ld.setValorParcela(lancamento.getValorParcela());

		atividadeService.salvarAtividade(Constante.ATUALIZACAO_DESPESA,
				", atualizou uma despesa");
	}

	@Transactional(readOnly = true)
	public Optional<LancamentoDespesa> buscarLancamentoDespesa(Long id) {
		return despesaRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public void deleteDespesa(Long id) {
		despesaRepository.deleteById(id);
		atividadeService.salvarAtividade(Constante.ATUALIZACAO_DESPESA,
				", excluio uma despesa com id: " + id);
	}

	@Transactional(readOnly = true)
	public LocalDate buscarDataVencimentoDespesa(Long id) {
		return despesaRepository.findDataVencimento(id);
	}
	
	@Transactional(readOnly = true)
	public LocalDate buscarDataVencimentoReceita(Long id) {
		return receitaRepository.findDataVencimento(id);
	}

}
