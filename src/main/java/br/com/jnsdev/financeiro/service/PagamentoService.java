package br.com.jnsdev.financeiro.service;

import java.time.LocalDate;
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
    public void atualizarDespesaDataPagamento(Long id, String dtPagamento) {
        LancamentoDespesa despesa = despesaRepository.findById(id).get();
        // DateTimeFormatter dtf = DateTimeFormatter.forPattern("yyyy-MM-dd");
        LocalDate dt = LocalDate.parse(dtPagamento);

        despesa.setDtPagamento(dt);
    }

}