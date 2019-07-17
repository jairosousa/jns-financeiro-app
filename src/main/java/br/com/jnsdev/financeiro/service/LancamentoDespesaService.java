package br.com.jnsdev.financeiro.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jnsdev.financeiro.domain.LancamentoDespesa;
import br.com.jnsdev.financeiro.repository.LancamentoDespesaRepository;

@Service
public class LancamentoDespesaService {

	@Autowired
	private LancamentoDespesaRepository repository;

	@Transactional(readOnly = false)
	public void gerarLancamentoParcelado(LancamentoDespesa lancamento) {
		List<LancamentoDespesa> despesas = new ArrayList<>();
		
		for (int i = 0; i < lancamento.getQtdParcelas(); i++) {
			LancamentoDespesa lanc = new LancamentoDespesa(lancamento);
			lanc.setNumParcela(i + 1);
			lanc.setDtVencimento(gerarDataVencimento(lancamento.getDtVencimento(), i));
			lanc.setValorParcela(gerarValorParcela(lancamento.getValor(), lancamento.getQtdParcelas()));
			despesas.add(lanc);
		}

		repository.saveAll(despesas);
	}

	private BigDecimal gerarValorParcela(BigDecimal valor, Integer qtdParcelas) {
		return valor.divide(new BigDecimal(qtdParcelas.toString()), BigDecimal.ROUND_UP);
	}

	private LocalDate gerarDataVencimento(LocalDate data, int i) {
		return data.plusDays(i * 30);
	}

}
