package br.com.jnsdev.financeiro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jnsdev.financeiro.domain.LancamentoDespesa;
import br.com.jnsdev.financeiro.repository.LancamentoDespesaRepository;

/**
 * PagamentoService
 */
@Service
public class PagamentoService {

	@Autowired
	private LancamentoDespesaRepository despesaRepository;

	@Transactional(readOnly = true)
	public List<LancamentoDespesa> buscarDespesasNãoPagasNoMes(Long id, int mes, int ano) {
		return despesaRepository.findByNotDataPagamento(id, mes, ano).orElse(new ArrayList<LancamentoDespesa>());
	}

	@Transactional(readOnly = true)
	public LancamentoDespesa buscarDespesas(Long id) {
		return despesaRepository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void atualizarDespesaDataPagamento(LancamentoDespesa deps) {
		LancamentoDespesa despesa = despesaRepository.findById(deps.getId()).get();
		despesa.setDtPagamento(deps.getDtPagamento());
		despesa.setValorParcela(deps.getValorParcela());
	}

}