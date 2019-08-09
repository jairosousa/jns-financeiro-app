package br.com.jnsdev.financeiro.service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jnsdev.financeiro.repository.LancamentoDespesaRepository;
import br.com.jnsdev.financeiro.repository.LancamentoReceitaRepository;
import br.com.jnsdev.financeiro.service.projection.DclienteVal;

@Service
public class DashboardService {

	@Autowired
	private LancamentoDespesaRepository despesaRepository;
	
	@Autowired
	private LancamentoReceitaRepository receitaRepository;
	
	@Transactional(readOnly = true)
	public DclienteVal getValoresMesUsuario(Long id, int mes, int ano) {
		Map<String, Object> json = new LinkedHashMap<>();
		BigDecimal tDespesas = despesaRepository.findSumMes(id, mes, ano).orElse(BigDecimal.ZERO);
		BigDecimal tReceita = receitaRepository.findSumMes(id, mes, ano).orElse(BigDecimal.ZERO);
		DclienteVal valores = new DclienteVal(tDespesas, tReceita);
		
		return valores;
	}
}
