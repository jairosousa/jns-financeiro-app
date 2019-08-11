package br.com.jnsdev.financeiro.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jnsdev.financeiro.repository.projection.LancamentoDespesaDTO;

public interface LancamentoDespesaRepositoryQuery {
	
	Page<LancamentoDespesaDTO> findAllBySearchByIdClienteByMonth(Long id, int mes, int ano, String search,
            Pageable pageable);

}
